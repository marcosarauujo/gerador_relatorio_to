package com.marcos.geradorrelatorioto.business.mapper;

import com.marcos.geradorrelatorioto.business.dto.in.SessaoRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.SessaoResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.entity.SessaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criancaEntity", ignore = true)
    SessaoEntity paraSessaoEntity(SessaoRequestDTO sessaoRequestDTO);

    @Mapping(source = "criancaEntity.nomeCrianca", target = "nomeCrianca")
    SessaoResponseDTO paraSessaoResponseDTO(SessaoEntity sessaoEntity);
}
