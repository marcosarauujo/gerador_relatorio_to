package com.marcos.geradorrelatorioto.business.mapper;

import com.marcos.geradorrelatorioto.business.dto.out.RelatorioResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.entity.RelatorioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RelatorioMapper {

    @Mapping(source = "criancaEntity.nomeCrianca", target = "nomeCrianca")
    RelatorioResponseDTO paraRelatorioResponseDTO(RelatorioEntity relatorioEntityentity);
}
