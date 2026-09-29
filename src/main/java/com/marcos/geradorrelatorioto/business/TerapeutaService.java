package com.marcos.geradorrelatorioto.business;

import com.marcos.geradorrelatorioto.business.dto.in.TerapeutaRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.TerapeutaResponseDTO;
import com.marcos.geradorrelatorioto.business.mapper.TerapeutaMapper;
import com.marcos.geradorrelatorioto.infrastructure.Repository.TerapeutaRepository;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import com.marcos.geradorrelatorioto.infrastructure.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TerapeutaService {
    private final TerapeutaRepository terapeutaRepository;
    private final TerapeutaMapper terapeutaMapper;
    private final PasswordEncoder passwordEncoder;

    public TerapeutaResponseDTO cadastrarTerapeuta(TerapeutaRequestDTO requestDTO) {
        TerapeutaEntity terapeutaEntity = terapeutaMapper.paraTerapeutaEntity(requestDTO);
        String SenhaCriptografa = passwordEncoder.encode(requestDTO.getSenha()
        );
        terapeutaEntity.setSenha(SenhaCriptografa);
        return terapeutaMapper.paraTerapeutaDTO(terapeutaRepository.save(terapeutaEntity)
        );
    }

    public TerapeutaEntity buscarTerapeutaEmail(String email) {
        try {
            return terapeutaRepository.findByEmail(email).orElseThrow(() ->
                    new ConflictException("Terapeuta não encontrada " + email));
        } catch (ConflictException e) {
            throw new ConflictException("Terapeuta não encontrada " + email, e.getCause());
        }
    }
}
