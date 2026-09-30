package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.SessaoService;
import com.marcos.geradorrelatorioto.business.dto.in.SessaoRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.SessaoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessao")
@RequiredArgsConstructor
public class SessaoController {
    private final SessaoService sessaoService;

    @PostMapping("/registrar")
    public ResponseEntity<SessaoResponseDTO> registrarSessao(@Valid @RequestBody SessaoRequestDTO requestDTO,
                                                             @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoService.registrarSessao(requestDTO, token));
    }

    @GetMapping("/listar-mes")
    public ResponseEntity<List<SessaoResponseDTO>> listarSessoesDoMes(@RequestParam Long criancaId,
                                                                      @RequestParam int ano,
                                                                      @RequestParam int mes,
                                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(sessaoService.listarSessoesDoMes(criancaId, ano, mes, token));
    }
}