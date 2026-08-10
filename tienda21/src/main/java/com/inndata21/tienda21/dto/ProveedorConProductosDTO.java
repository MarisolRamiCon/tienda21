package com.inndata21.tienda21.dto;

import java.util.List;

import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.entity.Proveedores;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProveedorConProductosDTO {

	private Proveedores proveedor;
	private List<Productos> productos;

	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}
}
