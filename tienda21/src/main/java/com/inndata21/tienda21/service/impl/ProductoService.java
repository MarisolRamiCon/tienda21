package  com.inndata21.tienda21.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.dto.response.ProductosResponse;
import com.inndata21.tienda21.entity.productos;
import com.inndata21.tienda21.repository.ProductosRespository;
import com.inndata21.tienda21.service.IProducto;

@Service
public class ProductoService implements IProducto {
    @Autowired
    ProductosRespository productoRepository;

    @Override 
    public List<productos> leerTodo() {
        return productoRepository.findAll();
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

    @Override
    public List<ProductosResponse> productosBaratos(Double precio) {
        return productoRepository.findByPrecioLessThan(precio).stream().map(
            productos -> {
                ProductosResponse productoResponse = new ProductosResponse();
                productoResponse.setNombreProducto(productos.getNombreProducto());
                productoResponse.setPrecio(productos.getPrecio());
                productoResponse.setStock(productos.getStock());
                return productoResponse;
            }
        ).toList();
    }

    @Override
    public List<ProductosResponse> productosProveedorStock(Integer proveedorId, Integer stock) {
        return productoRepository.findByProveedorIdIsAndStockGreaterThan(proveedorId, stock).stream().map(
            productos -> {
                ProductosResponse productoResponse = new ProductosResponse();
                productoResponse.setNombreProducto(productos.getNombreProducto());
                productoResponse.setPrecio(productos.getPrecio());
                productoResponse.setStock(productos.getStock());
                return productoResponse;
            }
        ).toList();
    }

    @Override
    public List<productos> productosBaratosQuery(Double Precio) {
        return productoRepository.productosBaratos(Precio);
    }

    @Override
    public String deleteLogico(Integer idProducto) {
        Optional<productos> productoOptional = productoRepository.findById(idProducto);
        if (productoOptional.isPresent()) {
            productos existingProducto = productoOptional.get();
            existingProducto.setActive(false);
            productoRepository.save(existingProducto);
            return "Producto eliminado lógicamente.";
        } else {
            return "Producto no encontrado.";
        }
    }

    @Override
    public String deleteFisico(Integer idProducto) {
        Optional<productos> productoOptional = productoRepository.findById(idProducto);
        if (productoOptional.isPresent()) {
            productos producto2 = productoOptional.get();
            productoRepository.delete(producto2);
            return "Producto eliminado físicamente.";
        } else {
            return "Producto no encontrado.";
        }
    }
}