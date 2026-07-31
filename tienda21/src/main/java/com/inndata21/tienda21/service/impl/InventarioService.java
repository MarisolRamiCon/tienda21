package com.inndata21.tienda21.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.dto.InventarioDTO;
import com.inndata21.tienda21.entity.Inventario;
import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.repository.InventarioRepository;
import com.inndata21.tienda21.repository.ProductosRepository;
import com.inndata21.tienda21.service.IInventarioService;

@Service
public class InventarioService implements IInventarioService {

	@Autowired
	InventarioRepository _inventarioRepository;
	@Autowired
	ProductosRepository _productosRepository;
	
	@Override
	public List<Inventario> readAll() {
		// TODO Auto-generated method stub
		return _inventarioRepository.findAll();
	}

	@Override
	public Inventario reabById(Integer id) {
		// TODO Auto-generated method stub
		return _inventarioRepository.findById(id).orElse(null);
	}

	@Override
	public Inventario create(InventarioDTO inventario) {

		Productos producto = _productosRepository.findById(inventario.getIdProducto())
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

	    Inventario nuevo = new Inventario();
	    nuevo.setProductos(producto);
	    nuevo.setStock(0);

	    return _inventarioRepository.save(nuevo);
		
	}

	@Override
	public Inventario updateById(Integer id, InventarioDTO inventario) {
		Optional<Inventario> inventarioActualizar= _inventarioRepository.findById(id);
		Optional<Productos> productoExistente= _productosRepository.findById(inventario.getIdProducto());
		if (productoExistente.isPresent()) {
	        Productos p = productoExistente.get();
	        
	        Inventario nuevo = inventarioActualizar.get();
	        nuevo.setProductos(p);
	        nuevo.setStock(inventario.getStock());
	        
	        return _inventarioRepository.save(nuevo); 
	        
	    } else {
	        return null; 
	    }
	}

}
