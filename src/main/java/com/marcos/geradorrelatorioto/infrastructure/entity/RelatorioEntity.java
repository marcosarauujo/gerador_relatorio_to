package com.marcos.geradorrelatorioto.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "relatorio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crianca_id", nullable = false)
    private CriancaEntity criancaEntity;

    @Column(nullable = false)
    private LocalDate mesReferencia;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String textoGerado;

    private LocalDate dataGeracao;
}
