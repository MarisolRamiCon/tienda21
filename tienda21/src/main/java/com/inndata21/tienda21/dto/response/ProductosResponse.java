package com.inndata21.tienda21.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductosResponse {
    private String nombreProducto;
    private Double precio;
    private Integer stock;
}
