package com.marcos.geradorrelatorioto.business;

import com.marcos.geradorrelatorioto.business.dto.in.CriancaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.CriancaResponseDTO;
import com.marcos.geradorrelatorioto.business.mapper.CriancaMapper;
import com.marcos.geradorrelatorioto.infrastructure.Repository.CriancaRepository;
import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import com.marcos.geradorrelatorioto.infrastructure.exceptions.ResourceNotFoundException;
import com.marcos.geradorrelatorioto.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriancaService {

    private final CriancaRepository criancaRepository;
    private final CriancaMapper criancaMapper;
    private final TerapeutaService terapeutaService;
    private final JwtUtil jwtUtil;

    public CriancaResponseDTO cadastraCrianca(CriancaRequestDTO criancaRequestDTO, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TerapeutaEntity terapeutaEntity = terapeutaService.buscarTerapeutaEmail(email);

        CriancaEntity criancaEntity = criancaMapper.paraCriancaEntity(criancaRequestDTO);
        criancaEntity.setTerapeutaEntity(terapeutaEntity);
        return criancaMapper.paraCriancaDTO(criancaRepository.save(criancaEntity)
        );
    }

    public List<CriancaResponseDTO> listarMinhasCriancas(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TerapeutaEntity terapeutaEntity = terapeutaService.buscarTerapeutaEmail(email);

        List<CriancaEntity> minhasCriancas = criancaRepository.findByTerapeutaEntity(terapeutaEntity);
        return minhasCriancas.stream()
                .map(criancaMapper::paraCriancaDTO)
                .toList();
    }

    public CriancaResponseDTO buscarCriancaPorId(Long criancaId) {
        CriancaEntity crianca = criancaRepository.findById(criancaId)
                .orElseThrow(() -> new ResourceNotFoundException("Criança não encontrada com id: " + criancaId));
        return criancaMapper.paraCriancaDTO(crianca);
    }
    public CriancaEntity buscarCriancaEntityPorId(Long criancaId) {
        return criancaRepository.findById(criancaId)
                .orElseThrow(() -> new ResourceNotFoundException("Criança não encontrada com id: " + criancaId));
    }


}
