package com.marcos.geradorrelatorioto.business;

import com.marcos.geradorrelatorioto.business.dto.out.RelatorioResponseDTO;
import com.marcos.geradorrelatorioto.business.dto.out.SessaoResponseDTO;
import com.marcos.geradorrelatorioto.business.mapper.RelatorioMapper;
import com.marcos.geradorrelatorioto.infrastructure.Repository.RelatorioRepository;
import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import com.marcos.geradorrelatorioto.infrastructure.entity.RelatorioEntity;
import com.marcos.geradorrelatorioto.infrastructure.exceptions.ArquivoConhecimentoException;
import com.marcos.geradorrelatorioto.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final RelatorioMapper relatorioMapper;
    private final CriancaService criancaService;
    private final SessaoService sessaoService;
    private final ChatModel chatModel; //interface do Spring AI que se comunica com o ChatGPT!

    public RelatorioResponseDTO gerarRelatorio(Long criancaId, int ano, int mes, String token) {
        CriancaEntity criancaEntity = criancaService.buscarCriancaPorId(criancaId);
        List<SessaoResponseDTO> sessaoResponseDTOS = sessaoService.listarSessoesDoMes(criancaId, ano, mes, token);

        if (sessaoResponseDTOS.isEmpty()) {
            throw new ResourceNotFoundException("Não há sessões registradas para esta criança neste mês.");
        }

        // Junta todas as anotações em um único texto para mandar para a IA
        StringBuilder anotacoesCompiladas = new StringBuilder();
        for (SessaoResponseDTO sessao : sessaoResponseDTOS) {
            anotacoesCompiladas.append("Data: ").append(sessao.getDataSessao()).append("\n");
            anotacoesCompiladas.append("Anotação: ").append(sessao.getAnotacoes()).append("\n\n");
        }

        String promptComando = """
                Aja como uma Terapeuta Ocupacional especializada em Integração Sensorial de Ayres.
                Escreva um relatório clínico de evolução mensal para um paciente, baseado EXCLUSIVAMENTE nas anotações das sessões abaixo.
                
                REGRAS DE ESCRITA:
                - Tom profissional, objetivo e em terceira pessoa.
                - Relacione os comportamentos observados com componentes sensoriais (Tátil, Vestibular, Proprioceptivo) e planejamento motor.
                - Evite termos julgadores (como "teimoso" ou "birrento"). Use "apresentou resistência", "baixa tolerância à frustração" ou "comportamento de evasão".
                - Relacione o processamento sensorial com o impacto funcional e a resposta adaptativa.
                - O texto deve ter uma introdução com o estado geral, um desenvolvimento com as demandas sensoriais e funcionais, e uma conclusão sobre a evolução no mês.
                - Não invente dados que não estão nas anotações.
                
                DADOS DO PACIENTE:
                Nome: %s
                Diagnóstico: %s
                
                ANOTAÇÕES DO MÊS:
                %s
                """;

        // Substitui os %s do texto acima pelos dados reais usando String.format()
        String promptFinal = String.format(promptComando,
                criancaEntity.getNomeCrianca(),
                criancaEntity.getDiagnostico(),
                anotacoesCompiladas.toString());

        String textoGeradoPelaIA = chatModel.call(promptFinal);

        LocalDate mesReferencia = LocalDate.of(ano, mes, 1);
        RelatorioEntity relatorio = RelatorioEntity.builder()
                .criancaEntity(criancaEntity)
                .mesReferencia(mesReferencia)
                .textoGerado(textoGeradoPelaIA)
                .dataGeracao(LocalDate.now())
                .build();

        return relatorioMapper.paraRelatorioResponseDTO(relatorioRepository.save(relatorio)
        );
    }

    public RelatorioResponseDTO buscarRelatorioPorId(Long id, String token) {
        RelatorioEntity relatorioEntity = relatorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relatório não encontrado com id: " + id));
        return relatorioMapper.paraRelatorioResponseDTO(relatorioEntity);
    }

    private String lerArquivoConhecimento() {
        try {
            ClassPathResource resource = new ClassPathResource("knowledge/base-larissa.md");
            return resource.getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ArquivoConhecimentoException(
                    "Não foi possível carregar a base de conhecimento clínico. " +
                            "Verifique se o arquivo 'knowledge/base-larissa.md' existe em resources/.", e
            );
        }
    }

    public List<RelatorioResponseDTO> listarRelatoriosPorCrianca(Long criancaId, String token) {

        // Verifica se a criança existe
        CriancaEntity crianca = criancaService.buscarCriancaPorId(criancaId);

        // Busca todos os relatórios dessa criança, do mais recente para o mais antigo
        return relatorioRepository.findByCriancaEntityOrderByMesReferenciaDesc(crianca)
                .stream()
                .map(relatorioMapper::paraRelatorioResponseDTO)
                .toList();
    }
}


