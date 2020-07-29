package org.man.fota;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.man.fota.controller.service.FeatureService;
import org.man.fota.controller.service.impl.FeatureServiceImpl;
import org.man.fota.model.mapper.FeatureMapper;
import org.man.fota.model.repository.FeatureRepositoryCustom;
import org.man.fota.model.repository.impl.VehicleRepository;
import org.man.fota.util.exceptions.VehicleNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

public class FeatureServiceTest {

    @Mock
    private VehicleRepository repository;

    @Mock
    private FeatureRepositoryCustom repositoryCustom;

    @Mock
    private FeatureMapper mapper;

    @InjectMocks
    private FeatureService featureService = new FeatureServiceImpl();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getIncompatibleFeaturesVehicleNotFound() {
        Mockito.when(repository.existsByUuidVin(any(UUID.class))).thenReturn(false);
        Assertions.assertThrows(VehicleNotFoundException.class, () -> {
            featureService.getIncompatibleFeatures(UUID.randomUUID());
        });
    }

    @Test
    public void getInstallableFeaturesVehicleNotFound() {
        Mockito.when(repository.existsByUuidVin(any(UUID.class))).thenReturn(false);
        Assertions.assertThrows(VehicleNotFoundException.class, () -> {
            featureService.getInstallableFeatures(UUID.randomUUID());
        });
    }

    @Test
    public void getIncompatibleFeaturesEmpty() {
        Mockito.when(repository.existsByUuidVin(any(UUID.class))).thenReturn(true);
        Mockito.when(repositoryCustom.findIncompatiblesFeaturesByUUID(any(UUID.class))).thenReturn(new HashSet<>());
        Mockito.when(mapper.toDTO(any(Set.class))).thenReturn(new HashSet<>());
        featureService.getIncompatibleFeatures(UUID.randomUUID());
    }

    @Test
    public void getInstallableFeaturesEmpy() {
        Mockito.when(repository.existsByUuidVin(any(UUID.class))).thenReturn(true);
        Mockito.when(repositoryCustom.findInstallableFeaturesByUUID(any(UUID.class))).thenReturn(new HashSet<>());
        Mockito.when(mapper.toDTO(any(Set.class))).thenReturn(new HashSet<>());
        featureService.getInstallableFeatures(UUID.randomUUID());
    }


}
