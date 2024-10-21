package com.codigo.springexamen1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "pedido")

public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    @JsonBackReference

    private PersonaEntity persona;

    @Column(updatable = false)
    private Timestamp fechaCreacion;

    private Timestamp fechaAnulacion;

}