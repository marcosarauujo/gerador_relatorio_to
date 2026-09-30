package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.TerapeutaService;
import com.marcos.geradorrelatorioto.business.dto.in.TerapeutaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.TerapeutaResponseDTO;
import com.marcos.geradorrelatorioto.business.mapper.TerapeutaMapper;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import com.marcos.geradorrelatorioto.infrastructure.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/terapeuta")
public class TerapeutaController {

    private final TerapeutaService terapeutaService;
    private final JwtUtil jwtUtil;
    private final TerapeutaMapper terapeutaMapper;

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public TerapeutaResponseDTO cadastrarTerapeuta(@Valid @RequestBody TerapeutaRequestDTO terapeutaRequestDTO) {
        return terapeutaService.cadastrarTerapeuta(terapeutaRequestDTO);
    }
    @GetMapping("/perfil")
    public ResponseEntity<TerapeutaResponseDTO> buscarTerapeutaEmail(@RequestHeader("Authorization") String token){

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TerapeutaEntity terapeutaEntity = terapeutaService.buscarTerapeutaEmail(email);
        return ResponseEntity.ok(terapeutaMapper.paraTerapeutaDTO(terapeutaEntity));
    }
}
