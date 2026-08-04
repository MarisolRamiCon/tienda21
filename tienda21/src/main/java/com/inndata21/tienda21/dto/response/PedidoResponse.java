package com.inndata21.tienda21.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PedidoResponse {
    private LocalDateTime fecha_pedido;
    private Integer cliente_id;
    private Double total_pedido;
}