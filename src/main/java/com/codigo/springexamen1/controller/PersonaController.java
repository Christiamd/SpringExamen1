package com.codigo.springexamen1.controller;

import com.codigo.springexamen1.entity.PersonaEntity;
import com.codigo.springexamen1.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona/v1")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/crearPersona")
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona) {
        PersonaEntity nuevaPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerPersonas")
    public List<PersonaEntity>obtenerTodasLasPersonas(){
        List<PersonaEntity>  personas ;
                return personas =personaService.obtenerTodasLasPersonas();
    }
    @GetMapping("/{documento}")
    public ResponseEntity<PersonaEntity> personaxDocumento(@PathVariable String documento) {
        return personaService.buscarPersonaxNumDocumento(documento)
                .map(ResponseEntity::ok) // Devuelve 200 OK con la persona si se encuentra
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Devuelve 404 Not Found si no se encuentra
    }

    @DeleteMapping("borrar/{documento}")
    public ResponseEntity<String> borrarPersona(@PathVariable String documento){
        return personaService.eliminarPersona(documento);
    }

}
