package com.inndata21.tienda21.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductosRequest {
    private String nombreProducto;
    private String descripcionProducto;
    private Double precio;
    private String categoria;
    private Integer proveedorId;
    private Integer stock;
}
