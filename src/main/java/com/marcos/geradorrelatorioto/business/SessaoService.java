package com.marcos.geradorrelatorioto.business;

import com.marcos.geradorrelatorioto.business.dto.in.SessaoRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.SessaoResponseDTO;
import com.marcos.geradorrelatorioto.business.mapper.SessaoMapper;
import com.marcos.geradorrelatorioto.infrastructure.Repository.SessaoRepository;
import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import com.marcos.geradorrelatorioto.infrastructure.entity.SessaoEntity;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import com.marcos.geradorrelatorioto.infrastructure.exceptions.AccessDeniedException;
import com.marcos.geradorrelatorioto.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoService {
    private final SessaoRepository sessaoRepository;
    private final SessaoMapper sessaoMapper;
    private final TerapeutaService terapeutaService;
    private final CriancaService criancaService;
    private final JwtUtil jwtUtil;


    public SessaoResponseDTO registrarSessao(SessaoRequestDTO sessaoRequestDTO, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TerapeutaEntity terapeutaEntity = terapeutaService.buscarTerapeutaEmail(email);

        CriancaEntity criancaEntity = criancaService.buscarCriancaEntityPorId(sessaoRequestDTO.getCriancaId());

        if (!criancaEntity.getTerapeutaEntity().getId().equals(terapeutaEntity.getId())) {
            throw new AccessDeniedException("Acesso negado");
        }
        SessaoEntity novaSessao = sessaoMapper.paraSessaoEntity(sessaoRequestDTO);
        novaSessao.setCriancaEntity(criancaEntity);

        SessaoEntity sessaoSalva = sessaoRepository.save(novaSessao);
        return sessaoMapper.paraSessaoResponseDTO(sessaoSalva);
    }

    public List<SessaoResponseDTO> listarSessoesDoMes(Long criancaId, int ano, int mes, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TerapeutaEntity terapeutaEntity = terapeutaService.buscarTerapeutaEmail(email);

        CriancaEntity criancaEntity = criancaService.buscarCriancaEntityPorId(criancaId);

        if (!criancaEntity.getTerapeutaEntity().getId().equals(terapeutaEntity.getId())) {
            throw new AccessDeniedException("Acesso negado");
        }

        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());

        return sessaoRepository.findByCriancaEntityAndDataSessaoBetween(criancaEntity, dataInicio, dataFim)
                .stream()
                .map(sessaoMapper::paraSessaoResponseDTO)
                .toList();
    }


}
