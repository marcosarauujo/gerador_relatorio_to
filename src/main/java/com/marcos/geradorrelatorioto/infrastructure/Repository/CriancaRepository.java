package com.marcos.geradorrelatorioto.infrastructure.Repository;

import com.marcos.geradorrelatorioto.infrastructure.entity.CriancaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriancaRepository extends JpaRepository<CriancaEntity,Long> {

}
