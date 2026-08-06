package com.inndata21.tienda21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.inndata21.tienda21.entity.DetallePedido;
import org.springframework.data.jpa.repository.Query;


public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    public List<DetallePedido> findByPedidoId(Integer pedidoId);
    

    @Query(value = "SELECT * FROM detalle_pedidos WHERE pedido_id = :pedidoId AND active = true", nativeQuery = true)
    public List<DetallePedido> findDetallesActivosByPedidoId(Integer pedidoId);

}
