package org.man.fota.model.repository.impl;

import org.man.fota.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Boolean existsByUuidVin(UUID uuidVin);
}
