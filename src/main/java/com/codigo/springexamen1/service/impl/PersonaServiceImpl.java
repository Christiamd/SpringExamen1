package com.codigo.springexamen1.service.impl;

import com.codigo.springexamen1.entity.EstadoPersona;
import com.codigo.springexamen1.entity.PersonaEntity;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.codigo.springexamen1.repository.PersonaRepository;
import com.codigo.springexamen1.service.PersonaService;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    private  final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona){
        persona.setFecha_creacion(new Timestamp(System.currentTimeMillis()));

        try {return personaRepository.save(persona);}catch (PersistenceException e){
            if (e.getCause() != null && e.getCause().getMessage().contains("llave duplicada")) {
                throw new RuntimeException("Llave duplicada viola restricción de unicidad: " + e.getMessage(), e);
            } else {
                throw e;
            }
        }

    }
    @Override
    public List<PersonaEntity> obtenerTodasLasPersonas() {
        return personaRepository.findActivos();
    }
    @Override
    public Optional<PersonaEntity> buscarPersonaxNumDocumento(String documento) {
        return personaRepository.findBynumDocumento(documento);
    }

    @Override
    public ResponseEntity<String> eliminarPersona(String numDocuemnto) {
        Optional<PersonaEntity> persona = buscarPersonaxNumDocumento(numDocuemnto);
        if(persona.isEmpty())return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        PersonaEntity personaObtenida = persona.get();
        personaObtenida.setEstado(EstadoPersona.INACTIVO);
        personaObtenida.setFecha_anulacion(new Timestamp(System.currentTimeMillis()));
        personaRepository.save(personaObtenida);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Devolver 204 No Content
    }

    @Override
    public PersonaEntity actualizarPersona(String numDocumento, PersonaEntity personaActualizad) {
        PersonaEntity personaObtenida =  buscarPersonaxNumDocumento(numDocumento)
                .orElseThrow(() -> new NoSuchElementException("Error Cliente no existe"));

        if(personaActualizad.getDireccionEntity() == null) personaActualizad.setDireccionEntity(personaObtenida.getDireccionEntity());
        personaActualizad.setNum_documento(personaObtenida.getNum_documento());
        if(personaActualizad.getPedidos()==null) personaActualizad.setPedidos(personaObtenida.getPedidos());

        return personaRepository.save(personaActualizad);

    }


}
