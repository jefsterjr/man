package org.man.fileprocessor.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.man.fileprocessor.exception.DirectoryNotFoundException;
import org.man.fileprocessor.service.DirectoryWatcherService;
import org.man.fileprocessor.service.FileProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DirectoryWatcherServiceImpl implements DirectoryWatcherService {

    final Logger logger = LoggerFactory.getLogger(DirectoryWatcherServiceImpl.class);

    @Autowired
    private FileProcessorService fileProcessorService;


    public void watch(final String directoryPath) throws IOException, InterruptedException, DirectoryNotFoundException {
        logger.info("Working directory" + directoryPath);
        getAllFilesFromDirectory(directoryPath);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(directoryPath);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;
        logger.info("Watching directory for new entries.");
        while ((key = watchService.take()) != null) {
            List<String> files = key.pollEvents().stream().map(watchEvent -> String.valueOf(watchEvent.context())).collect(Collectors.toList());
            fileProcessorService.getDataFromFile(files, directoryPath);
            key.reset();
        }
    }

    private void getAllFilesFromDirectory(final String directoryPath) throws IOException, DirectoryNotFoundException {
        logger.info("Reading files from the directory");
        List<String> files;
        Stream<Path> paths;
        try {
            paths = Files.walk(Paths.get(directoryPath));
        } catch (NoSuchFileException e) {
            throw new DirectoryNotFoundException(e);
        }
        files = paths.filter(file -> FilenameUtils.getExtension(file.getFileName().toString()).equals("csv")).map(file -> file.getFileName().toString()).collect(Collectors.toList());
        if (files.size() == 0) logger.warn("No files found in the directory");
        fileProcessorService.getDataFromFile(files, directoryPath);
    }
}
