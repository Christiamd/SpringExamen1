package com.codigo.springexamen1.repository;

import com.codigo.springexamen1.entity.PersonaEntity;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {


    @Query("SELECT u FROM PersonaEntity u WHERE u.num_documento = :documento and u.estado = 'ACTIVO'")
    Optional<PersonaEntity> findBynumDocumento(@Param("documento") String documento);

    @Query ("SELECT u FROM PersonaEntity u WHERE u.estado = 'ACTIVO'")
    List<PersonaEntity> findActivos();




}
