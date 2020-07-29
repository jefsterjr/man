package org.man.fota.controller.service;

import org.man.fota.model.dto.FeatureDTO;

import java.util.Set;
import java.util.UUID;

public interface FeatureService {
    Set<FeatureDTO> getInstallableFeatures(UUID vin);

    Set<FeatureDTO> getIncompatibleFeatures(UUID vin);
}
