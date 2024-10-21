package com.codigo.springexamen1.repository;

import com.codigo.springexamen1.entity.EstadoPedido;
import com.codigo.springexamen1.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository  extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByEstado(EstadoPedido estado);
    @Query("SELECT u from PedidoEntity u where u.persona.num_documento =:num_documento and u.estado<>'ELIMINADO'")
    List<PedidoEntity> findByPersonaNroDoc(@Param("num_documento") String documento);

    @Query ("SELECT u from PedidoEntity u WHERE u.estado <> 'ELIMINADO'")
    List<PedidoEntity> findPedidoActivo();



}
