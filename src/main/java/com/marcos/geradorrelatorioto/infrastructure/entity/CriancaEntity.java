package com.marcos.geradorrelatorioto.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "crianca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CriancaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeCrianca;
    @Column(nullable = false)
    private LocalDate dataNascimentoCrianca;
    @Column(nullable = false)
    private String diagnostico;

    @ManyToOne
    @JoinColumn(name = "terapeuta_id",nullable = false )
    private TerapeutaEntity terapeutaEntity;


}
