package org.man.fota.controller;

import org.man.fota.controller.service.FeatureService;
import org.man.fota.model.dto.FeatureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private FeatureService featureService;

    @GetMapping("/{vin}/installable")
    public ResponseEntity<Set<FeatureDTO>> getInstallableFeatures(@PathVariable(name = "vin") UUID vin) {
        return ResponseEntity.ok(featureService.getInstallableFeatures(vin));
    }

    @GetMapping("/{vin}/incompatible")
    public ResponseEntity<Set<FeatureDTO>> getIncompatibleFeatures(@PathVariable(name = "vin") UUID vin) {
        return ResponseEntity.ok(featureService.getIncompatibleFeatures(vin));
    }
}
