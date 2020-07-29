package org.man.fileprocessor.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.man.fileprocessor.exception.FileException;
import org.man.fileprocessor.model.Configuration;
import org.man.fileprocessor.model.Vehicle;
import org.man.fileprocessor.model.enums.ConfigurationType;
import org.man.fileprocessor.model.enums.FileType;
import org.man.fileprocessor.model.repository.ConfigurationRepository;
import org.man.fileprocessor.model.repository.FileRepository;
import org.man.fileprocessor.model.repository.VehicleRepository;
import org.man.fileprocessor.service.FileProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileProcessorServiceImpl implements FileProcessorService {

    Logger logger = LoggerFactory.getLogger(FileProcessorService.class);

    @Autowired
    private FileRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    private final Map<String, Long> configurations = new HashMap<>();

    private final Map<String, Long> vins = new HashMap<>();

    @Override
    @Transactional
    public void getDataFromFile(final List<String> files, final String directoryPath) {
        logger.info("Processing files");
        files.stream().filter(fileName -> FilenameUtils.getExtension(fileName).equals("csv") && (fileName.contains("soft") || fileName.contains("hard"))).forEach(fileName -> {
            try {
                final Path path = Paths.get(directoryPath + "/" + fileName);
                final String md5 = generateFileMD5Key(path);
                if (repository.existsByKey(md5)) {
                    logger.warn("This file was saved already!");
                    return;
                }


                final LineIterator it = FileUtils.lineIterator(new File(path.toString()), "UTF-8");
                try {
                    Vehicle vehicle = null;
                    while (it.hasNext()) {
                        final String[] line = it.nextLine().split(",");
                        final String vin = line[0];
                        final String code = line[1];

                        if (vehicle == null) vehicle = getVehicle(vin);

                        if (!vehicle.getVin().equals(vin)) {
                            vehicleRepository.save(vehicle);
                            vins.put(vehicle.getVin(), vehicle.getId());
                            vehicle = getVehicle(vin);
                        }
                        saveConfiguration(fileName, vehicle, code);
                    }
                } finally {
                    LineIterator.closeQuietly(it);
                }
                createFile(md5, fileName);
            } catch (IOException e) {
                throw new FileException(e);
            }
        });
    }

    private void saveConfiguration(final String fileName, Vehicle vehicle, final String code) {
        vehicle.addConfiguration(saveConfiguration(code, fileName));
    }

    private Configuration saveConfiguration(final String code, final String fileName) {
        Configuration configuration = null;
        Long idConfiguration = configurations.get(code);
        if (idConfiguration != null) {
            configuration = new Configuration();
            configuration.setId(idConfiguration);
            configuration.setType(ConfigurationType.getType(fileName));
            configuration.setCode(code);
        }
        if (configuration == null) {
            configuration = configurationRepository.findFirstByCode(code);
            if (configuration == null) {
                configuration = new Configuration(code, ConfigurationType.getType(fileName));
                configurationRepository.save(configuration);
            }
            configurations.put(configuration.getCode(), configuration.getId());
        }
        return configuration;
    }

    private Vehicle getVehicle(final String vin) {
        Long idVehicle = vins.get(vin);
        Vehicle vehicle = null;
        if (idVehicle != null) {
            vehicle = vehicleRepository.findById(idVehicle).orElse(null);
        }
        if (vehicle == null) {
            vehicle = vehicleRepository.findFirstByVin(vin);
            if (vehicle == null) vehicle = new Vehicle(UUID.nameUUIDFromBytes(vin.getBytes()), vin);
        }

        return vehicle;
    }

    private String generateFileMD5Key(final Path path) throws IOException {
        return DigestUtils.md5Hex(Files.newInputStream(path));
    }

    private void createFile(final String md5, final String fileName) {
        org.man.fileprocessor.model.File file = new org.man.fileprocessor.model.File();
        file.setKey(md5);
        file.setInsertDate(LocalDateTime.now());
        file.setName(fileName);
        file.setType(FileType.getType(fileName));
        repository.save(file);
    }
}
