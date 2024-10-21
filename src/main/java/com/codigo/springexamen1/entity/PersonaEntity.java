package com.codigo.springexamen1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "persona")


public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private String nombre;
    @Column (nullable = false, unique = true)
    private String num_documento;
    @Enumerated(EnumType.STRING)
    private EstadoPersona estado;
    private String email;
    @Column (columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false , updatable = false )
    private Timestamp fecha_creacion;
    private Timestamp fecha_anulacion;


    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidos;

    /*
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private DireccionEntity direccionEntity;
    */


}
