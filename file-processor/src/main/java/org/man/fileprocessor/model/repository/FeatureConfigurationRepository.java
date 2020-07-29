package org.man.fileprocessor.model.repository;

import org.man.fileprocessor.model.FeatureConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureConfigurationRepository extends JpaRepository<FeatureConfiguration, Long> {

}
