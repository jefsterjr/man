package org.man.fota.model.mapper;

import org.man.fota.model.Feature;
import org.man.fota.model.dto.FeatureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FeatureMapper extends BaseMapper<Feature, FeatureDTO> {
}
