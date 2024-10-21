package com.codigo.springexamen1.repository;

import com.codigo.springexamen1.entity.DireccionEntity;
import com.codigo.springexamen1.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DireccionRepository extends JpaRepository<DireccionEntity,Long> {
    @Query("SELECT u FROM PersonaEntity u WHERE u.num_documento = :documento")
    PersonaEntity findBynumDocumento(@Param("documento") String documento);
}
