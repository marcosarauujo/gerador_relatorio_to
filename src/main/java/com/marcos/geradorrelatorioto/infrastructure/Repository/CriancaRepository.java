package com.marcos.geradorrelatorioto.infrastructure.Repository;

import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriancaRepository extends JpaRepository<CriancaEntity, Long> {
    List<CriancaEntity> findByTerapeutaEntity(
            TerapeutaEntity terapeutaEntity);

}
