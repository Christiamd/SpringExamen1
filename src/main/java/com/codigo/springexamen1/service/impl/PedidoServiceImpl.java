package com.codigo.springexamen1.service;

import com.codigo.springexamen1.entity.EstadoPedido;
import com.codigo.springexamen1.entity.PedidoEntity;
import com.codigo.springexamen1.entity.PersonaEntity;
import com.codigo.springexamen1.repository.PedidoRepository;
import com.codigo.springexamen1.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    final PedidoRepository pedidoRepository;
    final PersonaRepository personaRepository;
    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, PersonaRepository personaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public List<PedidoEntity> crearPedido(String persona,List<PedidoEntity> pedidos) {
        PersonaEntity personaExistente = personaRepository.findBynumDocumento(persona)
                .orElseThrow(() -> new NoSuchElementException("Error Cliente no existe"));

        for (PedidoEntity pedido : pedidos) {
            pedido.setPersona(personaExistente);
            pedido.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            pedido.setEstado(EstadoPedido.PENDIENTE);
        }

        return pedidoRepository.saveAll(pedidos);

    }

    @Override
    public List<PedidoEntity> obtenerTodosLosPedidos() {
        return pedidoRepository.findPedidoActivo();
    }

    @Override
    public Optional<PedidoEntity> obtenerPedidoxId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public PedidoEntity actualizarPedido(Long id, PedidoEntity pedidoActualizado) {
        PedidoEntity pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));

        // Actualizar el pedido
        if(pedidoActualizado.getPersona()== null){
            pedidoActualizado.setPersona(pedidoExistente.getPersona());
        }
        if(pedidoActualizado.getEstado()== null){
            pedidoActualizado.setEstado(pedidoExistente.getEstado());
        }
        if(pedidoActualizado.getDescripcion()== null){
            pedidoActualizado.setDescripcion(pedidoExistente.getDescripcion());
        }
        pedidoExistente.setDescripcion(pedidoActualizado.getDescripcion());
        pedidoExistente.setEstado(pedidoActualizado.getEstado());
        pedidoExistente.setPersona(pedidoActualizado.getPersona());
        if(pedidoActualizado.getEstado()==EstadoPedido.ELIMINADO)deletePedido(pedidoExistente.getId());
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void deletePedido(Long id) {
        Optional<PedidoEntity> pedido = obtenerPedidoxId(id);
        if (pedido.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado con ID: " + id);
        }
        PedidoEntity pedidoOk = pedido.get();
        pedidoOk.setFechaAnulacion(new Timestamp(System.currentTimeMillis()));
        pedidoOk.setEstado(EstadoPedido.ELIMINADO);
        pedidoRepository.save(pedidoOk);
    }

    @Override
    public List<PedidoEntity> getPedidosByEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    @Override
    public List<PedidoEntity> getPedidosByPersonaNroDoc(String personaNroDoc) {
        return pedidoRepository.findByPersonaNroDoc(personaNroDoc);
    }
}
