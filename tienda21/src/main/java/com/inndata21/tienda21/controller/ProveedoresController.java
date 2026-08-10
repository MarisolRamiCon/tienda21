package com.inndata21.tienda21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inndata21.tienda21.dto.ProveedorDTO;
import com.inndata21.tienda21.service.IProveedoresService;

import response.ProveedorConProductosResponse;
import response.ProveedoresResponse;

@RestController
@RequestMapping("/api/v1")
public class ProveedoresController {

	@Autowired
	private IProveedoresService proveedoresService;

	@GetMapping("/proveedores")
	public ResponseEntity<List<ProveedoresResponse>> readAll(@RequestParam(required = false) String filtro) {
		return ResponseEntity.ok(proveedoresService.readAll(filtro));
	}

	@GetMapping("/proveedores/{id}")
	public ResponseEntity<ProveedoresResponse> readById(@PathVariable Integer id) {
		ProveedoresResponse proveedor = proveedoresService.readById(id);
		if (proveedor != null) {
			return ResponseEntity.ok(proveedor);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/proveedores")
	public ResponseEntity<ProveedoresResponse> create(@RequestBody ProveedorDTO dto) {
		ProveedoresResponse nuevo = proveedoresService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
	}

	@PutMapping("/proveedores/{id}")
	public ResponseEntity<ProveedoresResponse> updateById(@PathVariable Integer id, @RequestBody ProveedorDTO dto) {
		ProveedoresResponse actualizado = proveedoresService.updateById(id, dto);
		if (actualizado != null) {
			return ResponseEntity.ok(actualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/proveedores/{id}")
	public ResponseEntity<Void> deleteLogico(@PathVariable Integer id) {
		proveedoresService.deleteLogico(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/proveedores/{id}/productos")
	public ResponseEntity<ProveedorConProductosResponse> obtenerProveedorConProductos(@PathVariable Integer id) {
		ProveedorConProductosResponse response = proveedoresService.obtenerProveedorConProductos(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/proveedores/feign/{id}")
	public ResponseEntity<ProveedoresResponse> obtenerProveedorPorFeign(@PathVariable Integer id) {
		ProveedoresResponse response = proveedoresService.obtenerProveedorPorFeign(id);
		return ResponseEntity.ok(response);
	}
}
