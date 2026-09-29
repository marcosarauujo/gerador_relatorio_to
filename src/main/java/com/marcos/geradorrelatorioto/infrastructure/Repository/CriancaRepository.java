package com.marcos.geradorrelatorioto.infrastructure.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriancaRepository extends JpaRepository<CriancaRepository,Long> {
}
