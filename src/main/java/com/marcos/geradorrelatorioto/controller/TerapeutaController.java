package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.TerapeutaService;
import com.marcos.geradorrelatorioto.business.dto.in.TerapeutaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.TerapeutaResponseDTO;
import com.marcos.geradorrelatorioto.business.mapper.TerapeutaMapper;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import com.marcos.geradorrelatorioto.infrastructure.security.JwtUtil;
import com.marcos.geradorrelatorioto.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/terapeuta")

@Tag(name = "Terapeuta", description = "Cadastro e perfil da terapeuta")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class TerapeutaController {

    private final TerapeutaService terapeutaService;
    private final JwtUtil jwtUtil;
    private final TerapeutaMapper terapeutaMapper;

    @PostMapping("/criar")

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar terapeuta Ocupacional",
            description = "Cadastra uma nova terapeuta Ocupacional no sistema")
    @ApiResponse(responseCode = "201", description = "Terapeuta Ocupacional cadastrada com sucesso")
    @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")

    public TerapeutaResponseDTO cadastrarTerapeuta(@Valid @RequestBody TerapeutaRequestDTO terapeutaRequestDTO) {
        return terapeutaService.cadastrarTerapeuta(terapeutaRequestDTO);
    }

    @GetMapping("/perfil")

    @Operation(summary = "Buscar perfil da Terapeuta Ocupacional",
            description = "Retorna os dados da terapeuta Ocupacional logada")
    @ApiResponse(responseCode = "200", description = "Perfil encontrado")
    @ApiResponse(responseCode = "404", description = "Terapeuta Ocupacional não encontrada")

    public ResponseEntity<TerapeutaResponseDTO> buscarTerapeutaEmail(

            @RequestHeader(name = "Authorization", required = false) String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TerapeutaEntity terapeutaEntity = terapeutaService.buscarTerapeutaEmail(email);
        return ResponseEntity.ok(terapeutaMapper.paraTerapeutaDTO(terapeutaEntity));
    }
}
