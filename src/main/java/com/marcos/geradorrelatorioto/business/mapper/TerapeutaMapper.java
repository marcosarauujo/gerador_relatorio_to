package com.marcos.geradorrelatorioto.business.mapper;

import com.marcos.geradorrelatorioto.business.dto.in.TerapeutaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.TerapeutaResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TerapeutaMapper {

    @Mapping(target = "id", ignore = true)
    TerapeutaEntity paraTerapeutaEntity(TerapeutaRequestDTO terapeutaRequestDTO);
    TerapeutaResponseDTO paraTerapeutaDTO(TerapeutaEntity terapeutaEntity);
}
