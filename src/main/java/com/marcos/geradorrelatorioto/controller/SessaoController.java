package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.SessaoService;
import com.marcos.geradorrelatorioto.business.dto.in.SessaoRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.CriancaResponseDTO;
import com.marcos.geradorrelatorioto.business.dto.out.SessaoResponseDTO;
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
@RequestMapping("/sessao")
@RequiredArgsConstructor

@Tag(name = "Sessão", description = "Registro das sessões de terapia")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class SessaoController {
    private final SessaoService sessaoService;

    @PostMapping("/registrar")

    @Operation(summary = "Registrar sessão", description = "Registra as anotações de uma sessão de terapia")
    @ApiResponse(responseCode = "201", description = "Sessão registrada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado — criança não pertence à terapeuta logada")
    @ApiResponse(responseCode = "404", description = "Criança não encontrada")

    public ResponseEntity<SessaoResponseDTO> registrarSessao(
            @Valid @RequestBody SessaoRequestDTO requestDTO,
            @Parameter(hidden = true) @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoService.registrarSessao(requestDTO, token));
    }

    @GetMapping("/listar-mes")


    @Operation(summary = "Listar sessões do mês",
            description = "Lista todas as sessões de uma criança em um mês específico")
    @ApiResponse(responseCode = "200", description = "Sessões retornadas com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "404", description = "Criança não encontrada")

    public ResponseEntity<List<SessaoResponseDTO>> listarSessoesDoMes(
            @RequestParam Long criancaId,
            @RequestParam int ano,
            @RequestParam int mes,
            @Parameter(hidden = true) @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(sessaoService.listarSessoesDoMes(criancaId, ano, mes, token));
    }


}