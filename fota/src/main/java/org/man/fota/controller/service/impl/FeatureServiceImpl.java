package org.man.fota.controller.service.impl;

import org.man.fota.controller.service.FeatureService;
import org.man.fota.model.Feature;
import org.man.fota.model.dto.FeatureDTO;
import org.man.fota.model.mapper.FeatureMapper;
import org.man.fota.model.repository.FeatureRepositoryCustom;
import org.man.fota.model.repository.impl.VehicleRepository;
import org.man.fota.util.exceptions.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepositoryCustom repositoryCustom;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private FeatureMapper mapper;

    @Override
    public Set<FeatureDTO> getInstallableFeatures(UUID vin) {
        verifyVehicle(vin);
        Set<Feature> features = repositoryCustom.findInstallableFeaturesByUUID(vin);
        return mapper.toDTO(features);

    }

    @Override
    public Set<FeatureDTO> getIncompatibleFeatures(UUID vin) {
        verifyVehicle(vin);
        Set<Feature> features = repositoryCustom.findIncompatiblesFeaturesByUUID(vin);
        return mapper.toDTO(features);
    }

    private Boolean verifyVehicle(UUID vin) {
        if (vehicleRepository.existsByUuidVin(vin)) {
            return true;
        }
        throw new VehicleNotFoundException();
    }
}
