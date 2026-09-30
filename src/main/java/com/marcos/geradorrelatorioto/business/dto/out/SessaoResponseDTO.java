package com.marcos.geradorrelatorioto.business.dto.out;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SessaoResponseDTO {
    private Long id;
    private LocalDate dataSessao;
    private String anotacoes;
    private String nomeCrianca;
}
