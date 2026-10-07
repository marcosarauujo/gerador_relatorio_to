package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.CriancaService;
import com.marcos.geradorrelatorioto.business.dto.in.CriancaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.CriancaResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crianca")
@RequiredArgsConstructor

@Tag(name = "Criança", description = "Gerenciamento dos pacientes")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class CriancaController {
    private final CriancaService criancaService;

    @PostMapping("/cadastrar")

    @Operation(summary = "Cadastrar criança", description = "Cadastra um novo paciente vinculado à terapeuta logada")
    @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso")

    public ResponseEntity<CriancaResponseDTO> cadastrarCrianca(
            @Valid @RequestBody CriancaRequestDTO criancaRequestDTO,
            @Parameter(hidden = true) @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                criancaService.cadastraCrianca(criancaRequestDTO, token)
        );
    }

    @GetMapping("/listar")

    @Operation(summary = "Listar pacientes", description = "Lista todos os pacientes da terapeuta logada")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")

    public ResponseEntity<List<CriancaResponseDTO>> listarMinhasCriancas(
            @Parameter(hidden = true) @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(criancaService.listarMinhasCriancas(token));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar criança por ID",
            description = "Retorna os dados de uma criança específica pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Criança encontrada com sucesso")
    public ResponseEntity<CriancaResponseDTO> buscarCriancaPorId(
            @PathVariable Long id,
            @Parameter(hidden = true) @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(criancaService.buscarCriancaPorId(id));
    }
}
