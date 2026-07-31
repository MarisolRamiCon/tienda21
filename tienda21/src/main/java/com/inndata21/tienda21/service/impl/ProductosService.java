package com.inndata21.tienda21.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.repository.ProductosRepository;
import com.inndata21.tienda21.service.IProductosService;

@Service
public class ProductosService implements IProductosService {

	@Autowired
	ProductosRepository _productosRepository;
	
	@Override
	public List<Productos> readAll() {
		// TODO Auto-generated method stub
		return _productosRepository.findAll();
	}

	@Override
	public Productos reabById(Integer id) {
		// TODO Auto-generated method stub
		return _productosRepository.findById(id).orElse(null);
	}

	@Override
	public Productos create(Productos inventario) {
		// TODO Auto-generated method stub
		return _productosRepository.save(inventario);
	}

	@Override
	public Productos updateById(Integer id, Productos producto) {
		Optional<Productos> productoExistente= _productosRepository.findById(id);
		if (productoExistente.isPresent()) {
	        Productos p = productoExistente.get();
	        
	        p.setNombreProducto(producto.getNombreProducto());
	        p.setDescripcion(producto.getDescripcion());
	        p.setCategoria(producto.getCategoria());
	        p.setPrecio(producto.getPrecio());
	        p.setStock(producto.getStock());
	        p.setProveedorId(producto.getProveedorId());
	        
	        return _productosRepository.save(p); 
	    } else {
	        return null; 
	    }
	}
}