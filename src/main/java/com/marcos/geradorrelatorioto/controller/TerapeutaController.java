package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.TerapeutaService;
import com.marcos.geradorrelatorioto.business.dto.in.TerapeutaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.TerapeutaResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import jakarta.persistence.GeneratedValue;
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

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public TerapeutaResponseDTO cadastrarTerapeuta(@Valid @RequestBody TerapeutaRequestDTO terapeutaRequestDTO) {
        return terapeutaService.cadastrarTerapeuta(terapeutaRequestDTO);
    }
    @GetMapping("/buscar")
    public ResponseEntity<TerapeutaEntity> buscarTerapeutaEmail(@RequestParam String email){
        return ResponseEntity.ok(terapeutaService.buscarTerapeutaEmail(email));
    }
}
