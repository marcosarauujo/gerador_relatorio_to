package com.marcos.geradorrelatorioto.business.dto.out;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RelatorioResponseDTO {
    private Long id;
    private String nomeCrianca;
    private LocalDate mesReferencia;
    private String textoGerado;
    private LocalDate dataGeracao;
}
