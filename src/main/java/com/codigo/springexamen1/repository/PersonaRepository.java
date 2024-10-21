package com.codigo.springexamen1.repository;

import com.codigo.springexamen1.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {


    @Query("SELECT u FROM PersonaEntity u WHERE u.num_documento = :documento and u.estado = 'ACTIVO'")
    Optional<PersonaEntity> findBynumDocumento(@Param("documento") String documento);




}
