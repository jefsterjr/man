package org.man.fota.model.mapper;

import org.mapstruct.MappingTarget;

import java.util.Set;

public interface BaseMapper<E, DTO> {

    DTO toDTO(E entity);

    E toEntity(DTO dto);

    Set<DTO> toDTO(Set<E> e);

    Set<E> toEntity(Set<DTO> dto);


    E map(DTO dto, @MappingTarget E entity);

}
