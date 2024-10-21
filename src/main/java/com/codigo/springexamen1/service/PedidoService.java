package com.codigo.springexamen1.service;

import com.codigo.springexamen1.entity.EstadoPedido;
import com.codigo.springexamen1.entity.PedidoEntity;
import com.codigo.springexamen1.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<PedidoEntity> crearPedido(String persona,List<PedidoEntity> pedido);
    List<PedidoEntity> obtenerTodosLosPedidos();
    Optional<PedidoEntity> obtenerPedidoxId(Long id);
    PedidoEntity actualizarPedido(Long id, PedidoEntity pedidoActualizado);
    void deletePedido(Long id);
    List<PedidoEntity> getPedidosByEstado(EstadoPedido estado);
    List<PedidoEntity> getPedidosByPersonaNroDoc(String personaNroDoc);
}
