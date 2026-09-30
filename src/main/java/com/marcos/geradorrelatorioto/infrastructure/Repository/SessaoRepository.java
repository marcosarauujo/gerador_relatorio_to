package com.marcos.geradorrelatorioto.infrastructure.Repository;

import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import com.marcos.geradorrelatorioto.infrastructure.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {

    List<SessaoEntity> findByCriancaEntityAndDataSessaoBetween(CriancaEntity criancaEntity,
                                                               LocalDate dataInicio,
                                                               LocalDate dataFim);
}
