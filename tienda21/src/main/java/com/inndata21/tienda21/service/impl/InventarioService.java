package com.inndata21.tienda21.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.dto.InventarioDTO;
import com.inndata21.tienda21.entity.Inventario;
import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.repository.InventarioRepository;
import com.inndata21.tienda21.repository.ProductosRepository;
import com.inndata21.tienda21.service.IInventarioService;

import response.InventarioResponse;
import response.ProductosResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class InventarioService implements IInventarioService {

	@Autowired
	InventarioRepository _inventarioRepository;
	@Autowired
	ProductosRepository _productosRepository;
	
	@Override
	public List<InventarioResponse> readAll(String filtro) {
		List<Inventario> lista = (filtro != null && !filtro.isBlank())
                ? _inventarioRepository.findByProductosNombreProductoContainingIgnoreCaseAndActivoTrue(filtro)
                : _inventarioRepository.findByActivoTrue();

        return lista.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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
	
	public void deleteLogico(Integer id) {
		Inventario inventario = _inventarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Registro no encontrado con ID: " + id));
        
        inventario.setActivo(false);
        _inventarioRepository.save(inventario);
    }

    // Mapeador auxiliar de Entidad JPA a Response DTO
    private InventarioResponse mapToResponse(Inventario entidad) {
    	ProductosResponse prodResp = null;
        if (entidad.getProductos() != null) {
            prodResp = new ProductosResponse();
            prodResp.setNombreProducto(entidad.getProductos().getNombreProducto());
            prodResp.setPrecio(entidad.getProductos().getPrecio());
            prodResp.setStock(entidad.getProductos().getStock());
        }

        InventarioResponse response = new InventarioResponse();
        response.setIdInventario(entidad.getIdInventario());
        response.setProducto(prodResp);
        response.setStock(entidad.getStock());
        response.setActivo(entidad.getActivo());
        return response;
    }
    

}
