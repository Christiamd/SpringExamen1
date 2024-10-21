package com.codigo.springexamen1.repository;

import com.codigo.springexamen1.entity.EstadoPedido;
import com.codigo.springexamen1.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository  extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByEstado(EstadoPedido estado);
    List<PedidoEntity> findByPersonaId(Long personaId);

}
