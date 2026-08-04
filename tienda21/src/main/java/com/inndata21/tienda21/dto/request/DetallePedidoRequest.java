package com.inndata21.tienda21.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DetallePedidoRequest {
  private Integer pedidoId;
  private Integer productoId; 
}
