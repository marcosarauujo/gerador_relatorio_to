package com.marcos.geradorrelatorioto.business.mapper;

import com.marcos.geradorrelatorioto.business.dto.in.CriancaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.CriancaResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CriancaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "terapeutaEntity", ignore = true)
    CriancaEntity paraCriancaEntity(CriancaRequestDTO criancaRequestDTO);
    CriancaResponseDTO paraCriancaDTO(CriancaEntity criancaEntity);
}
