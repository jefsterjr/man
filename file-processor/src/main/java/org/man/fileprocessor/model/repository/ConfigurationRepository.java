package org.man.fileprocessor.model.repository;

import org.man.fileprocessor.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Configuration findFirstByCode(String code);
}

