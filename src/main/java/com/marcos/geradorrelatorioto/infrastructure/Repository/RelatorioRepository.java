package com.marcos.geradorrelatorioto.infrastructure.Repository;

import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import com.marcos.geradorrelatorioto.infrastructure.entity.RelatorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelatorioRepository extends JpaRepository<RelatorioEntity, Long> {

    Optional<RelatorioEntity> findByCriancaEntityAndMesReferencia(CriancaEntity crianca,
                                                                  LocalDate mesReferencia);

    List<RelatorioEntity> findByCriancaEntityOrderByMesReferenciaDesc(CriancaEntity crianca);
}
