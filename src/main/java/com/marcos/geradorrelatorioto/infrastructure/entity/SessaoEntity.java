package com.marcos.geradorrelatorioto.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataSessao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String anotacoes;

    @ManyToOne
    @JoinColumn(name = "crianca_id", nullable = false)
    private CriancaEntity criancaEntity;

}
