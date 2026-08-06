package com.inndata21.tienda21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inndata21.tienda21.entity.pedido;


public interface PedidoRepository extends JpaRepository<pedido, Integer> {

    public List<pedido> findByClienteId(Integer clienteId);
    public List<pedido> findByFechaPedidoIs(String fecha_pedido);

    @Query(value = "SELECT * FROM pedidos WHERE cliente_id = :clienteId AND active = true", nativeQuery = true)
    public List<pedido> findPedidosActivosByClienteId(Integer clienteId);
}