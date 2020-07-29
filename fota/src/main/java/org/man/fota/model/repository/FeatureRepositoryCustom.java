package org.man.fota.model.repository;

import org.man.fota.model.Feature;

import java.util.Set;
import java.util.UUID;

public interface FeatureRepositoryCustom {

    Set<Feature> findIncompatiblesFeaturesByUUID(UUID uuidVin);

    Set<Feature> findInstallableFeaturesByUUID(UUID uuidVin);
}
