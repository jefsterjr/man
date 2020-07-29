package org.man.fileprocessor.model.repository;

import org.man.fileprocessor.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findFirstByVin(String vin);
}
