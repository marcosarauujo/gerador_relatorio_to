package com.marcos.geradorrelatorioto.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "terapeuta")
public class TerapeutaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeTerapeuta;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
}
