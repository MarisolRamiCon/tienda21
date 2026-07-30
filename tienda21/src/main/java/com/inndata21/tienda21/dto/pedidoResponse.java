package com.inndata21.tienda21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class pedidoResponse {
    private Integer idPedido;
    private String fecha_pedido;
    private Integer cliente_id;
    private Double total_pedido;
}
