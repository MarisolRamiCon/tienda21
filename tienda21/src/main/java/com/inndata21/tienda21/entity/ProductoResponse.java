package com.tienda.inndata021.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductoResponse {
    private Integer idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Double precio;
    private String categoria;
    private Integer proveedorId;
    private Integer stock;
}
