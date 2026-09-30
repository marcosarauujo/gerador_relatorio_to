package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.RelatorioService;
import com.marcos.geradorrelatorioto.business.dto.out.RelatorioResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor

@Tag(name = "Relatório", description = "Geração e consulta de relatórios clínicos com IA")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class RelatorioController {

    private final RelatorioService relatorioService;

    @PostMapping("/gerar")

    @Operation(summary = "Gerar relatório", description = "Gera um relatório clínico usando Inteligência Artificial")
    @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso")
    @ApiResponse(responseCode = "404", description = "Sem sessões registradas no mês")
    @ApiResponse(responseCode = "403", description = "Acesso negado")

    public ResponseEntity<RelatorioResponseDTO> gerarRelatorio(
            @RequestParam Long criancaId,
            @RequestParam int ano,
            @RequestParam int mes,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(relatorioService.gerarRelatorio(criancaId, ano, mes, token)
        );
    }

    @GetMapping("/buscar/{id}")

    @Operation(summary = "Buscar relatório por ID", description = "Retorna um relatório já gerado pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Relatório encontrado")
    @ApiResponse(responseCode = "404", description = "Relatório não encontrado")

    public ResponseEntity<RelatorioResponseDTO> buscarRelatorio(
            @PathVariable Long id,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(relatorioService.buscarRelatorioPorId(id, token));
    }

    @GetMapping("/listar/{criancaId}")

    @Operation(summary = "Listar relatórios por criança", description = "Lista todos os relatórios gerados para uma criança")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @ApiResponse(responseCode = "404", description = "Criança não encontrada")

    public ResponseEntity<List<RelatorioResponseDTO>> listarRelatorios
            (@PathVariable Long criancaId,
             @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(relatorioService.listarRelatoriosPorCrianca(criancaId, token));
    }
}
