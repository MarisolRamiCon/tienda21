package com.inndata21.tienda21.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DetallePedidoResponse {
    private Integer pedidoId;
    private Integer productoId;
}
