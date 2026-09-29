package com.marcos.geradorrelatorioto.infrastructure.Repository;

import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerapeutaRepository extends JpaRepository<TerapeutaEntity,Long> {
    Optional<TerapeutaEntity> findByEmail(String email);
}
