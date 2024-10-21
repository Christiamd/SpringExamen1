package com.codigo.springexamen1.controller;

import com.codigo.springexamen1.entity.EstadoPedido;
import com.codigo.springexamen1.entity.PedidoEntity;
import com.codigo.springexamen1.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido/v1")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Crear un nuevo pedido
    @PostMapping("/crear/{persona}")
    public ResponseEntity<PedidoEntity> crearPedido(@PathVariable String persona,@RequestBody PedidoEntity pedido) {
        PedidoEntity nuevoPedido = pedidoService.crearPedido(persona,pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    // Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<PedidoEntity>> obtenerTodosLosPedidos() {
        List<PedidoEntity> pedidos = pedidoService.obtenerTodosLosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Obtener un pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> obtenerPedidoPorId(@PathVariable Long id) {
        Optional<PedidoEntity> pedido = pedidoService.obtenerPedidoxId(id);
        return pedido.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> actualizarPedido(@PathVariable Long id, @RequestBody PedidoEntity pedidoActualizado) {
        PedidoEntity pedido = pedidoService.actualizarPedido(id, pedidoActualizado);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    // Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener pedidos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PedidoEntity>> obtenerPedidosPorEstado(@PathVariable EstadoPedido estado) {
        List<PedidoEntity> pedidos = pedidoService.getPedidosByEstado(estado);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Obtener pedidos por ID de persona
    @GetMapping("/persona/{personaId}")
    public ResponseEntity<List<PedidoEntity>> obtenerPedidosPorPersonaId(@PathVariable Long personaId) {
        List<PedidoEntity> pedidos = pedidoService.getPedidosByPersonaId(personaId);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
}
