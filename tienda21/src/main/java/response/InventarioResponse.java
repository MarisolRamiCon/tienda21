package response;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class InventarioResponse {
	private Integer idInventario;
    private ProductosResponse producto;
    private Integer stock;
    private Boolean activo;
    
	public Integer getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}
	public ProductosResponse getProducto() {
		return producto;
	}
	public void setProducto(ProductosResponse producto) {
		this.producto = producto;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	    
	    
}
