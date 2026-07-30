package  com.tienda.inndata021.service.implemetacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.inndata021.dto.ProductoResponse;
import com.tienda.inndata021.entity.detallePedido;
import com.tienda.inndata021.entity.productos;
import com.tienda.inndata021.repository.ProductosRespository;
import com.tienda.inndata021.service.IProducto;

@Service
public class ProductoService implements IProducto {
    @Autowired
    ProductosRespository productoRepository;
    
    @Override
    public List<ProductoResponse> leerTodo() {
        return productoRepository.findAll().stream().map(productos -> 
            new ProductoResponse(
                productos.getIdProducto(),
                productos.getNombreProducto(),
                productos.getDescripcionProducto(),
                productos.getPrecio(),
                productos.getCategoria(),
                productos.getProveedorId(),
                productos.getStock()
            )).toList();
    }

    @Override
    public productos readById(Integer idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

    @Override
    public productos create(productos prductos) {
        return productoRepository.save(prductos);
    }

    @Override
    public productos update(Integer idProducto, productos prductos) {
         Optional<productos> productoOptional = productoRepository.findById(idProducto);
        if (productoOptional.isPresent()) {
            productos existingProducto = productoOptional.get();
            existingProducto.setIdProducto(prductos.getIdProducto());
            existingProducto.setNombreProducto(prductos.getNombreProducto());
            existingProducto.setDescripcionProducto(prductos.getDescripcionProducto());
            existingProducto.setPrecio(prductos.getPrecio());
            existingProducto.setCategoria(prductos.getCategoria());
            existingProducto.setProveedorId(prductos.getProveedorId());
            existingProducto.setStock(prductos.getStock());
            return productoRepository.save(existingProducto);
        } else {
            return new productos();
        }
    }

    @Override
    public String delete(Integer idProducto) {
         Optional<productos> ProductoOptional = productoRepository.findById(idProducto);
        if (ProductoOptional.isPresent()) {
            productoRepository.deleteById(idProducto);
            return "Detalle de pedido eliminado correctamente.";
        } else {
            return "Detalle de pedido no encontrado.";
        }
    }

       
}