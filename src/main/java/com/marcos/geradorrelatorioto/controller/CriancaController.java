package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.CriancaService;
import com.marcos.geradorrelatorioto.business.dto.in.CriancaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.CriancaResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crianca")
@RequiredArgsConstructor
public class CriancaController {
    private final CriancaService criancaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<CriancaResponseDTO> cadastrarCrianca(@Valid @RequestBody CriancaRequestDTO criancaRequestDTO,
                                                               @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                criancaService.cadastraCrianca(criancaRequestDTO, token)
        );
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CriancaResponseDTO>> listarMinhasCriancas(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(criancaService.listarMinhasCriancas(token));
    }
}
