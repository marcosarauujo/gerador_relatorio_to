package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.RelatorioService;
import com.marcos.geradorrelatorioto.business.dto.out.RelatorioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @PostMapping("/gerar")
    public ResponseEntity<RelatorioResponseDTO> gerarRelatorio(
            @RequestParam Long criancaId,
            @RequestParam int ano,
            @RequestParam int mes,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(relatorioService.gerarRelatorio(criancaId, ano, mes, token)
        );
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<RelatorioResponseDTO> buscarRelatorio(@PathVariable Long id,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(relatorioService.buscarRelatorioPorId(id, token));
    }

    @GetMapping("/listar/{criancaId}")
    public ResponseEntity<List<RelatorioResponseDTO>> listarRelatorios(@PathVariable Long criancaId,
                                                                       @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(relatorioService.listarRelatoriosPorCrianca(criancaId, token));
    }
}
