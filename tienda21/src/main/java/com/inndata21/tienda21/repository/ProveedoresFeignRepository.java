package com.inndata21.tienda21.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import response.ProveedorConProductosResponse;
import response.ProveedoresResponse;

@FeignClient(name = "proveedoresFeignRepository", url = "${feign.client.proveedores.url:http://localhost:8080/api/v1}")
public interface ProveedoresFeignRepository {

	@GetMapping("/proveedores")
	List<ProveedoresResponse> obtenerTodosLosProveedores();

	@GetMapping("/proveedores/{id}")
	ProveedoresResponse obtenerProveedorPorId(@PathVariable("id") Integer id);

	@GetMapping("/proveedores/{id}/productos")
	ProveedorConProductosResponse obtenerProveedorConProductos(@PathVariable("id") Integer id);
}
