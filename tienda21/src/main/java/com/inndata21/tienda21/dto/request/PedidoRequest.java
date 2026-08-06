package com.inndata21.tienda21.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PedidoRequest {
    private Integer idPedido;
    private LocalDateTime fecha_pedido;
    private Integer cliente_id;
    private Double total_pedido;
}
