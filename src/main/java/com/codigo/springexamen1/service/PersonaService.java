package com.codigo.springexamen1.service;

import com.codigo.springexamen1.entity.PersonaEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    PersonaEntity crearPersona(PersonaEntity Persona);
    List<PersonaEntity> obtenerTodasLasPersonas();
    Optional<PersonaEntity> buscarPersonaxNumDocumento(String nrodocumento);

    ResponseEntity<String> eliminarPersona(String numDocumento);



    /*
    PersonaEntity actualizarPersona(Long id, PersonaEntity Persona);
    public PersonaEntity findBynum_documento(String documento);
    */


}
