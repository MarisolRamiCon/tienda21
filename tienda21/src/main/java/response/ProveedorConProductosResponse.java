package response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProveedorConProductosResponse {

	private ProveedoresResponse proveedor;
	private List<ProductosResponse> productos;

	public ProveedoresResponse getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedoresResponse proveedor) {
		this.proveedor = proveedor;
	}

	public List<ProductosResponse> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosResponse> productos) {
		this.productos = productos;
	}
}
