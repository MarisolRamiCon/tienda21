package com.inndata21.tienda21.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inndata21.tienda21.dto.InventarioDTO;
import com.inndata21.tienda21.entity.Inventario;
import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.repository.InventarioRepository;
import com.inndata21.tienda21.repository.ProductosRepository;
import com.inndata21.tienda21.service.impl.InventarioService;

import response.InventarioResponse;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

	@Mock
	private InventarioRepository inventarioRepository;

	@Mock
	private ProductosRepository productosRepository;

	@InjectMocks
	private InventarioService inventarioService;

	private Inventario inventarioEjemplo;
	private Productos productoEjemplo;

	@BeforeEach
	void setUp() {
		productoEjemplo = new Productos();
		productoEjemplo.setIdProducto(1);
		productoEjemplo.setNombreProducto("Laptop");
		productoEjemplo.setPrecio(1500.0);
		productoEjemplo.setStock(10);

		inventarioEjemplo = new Inventario();
		inventarioEjemplo.setIdInventario(10);
		inventarioEjemplo.setProductos(productoEjemplo);
		inventarioEjemplo.setStock(5);
		inventarioEjemplo.setActivo(true);
	}

	@Test
	@DisplayName("ReadAll sin filtro retorna lista completa de respuestas")
	void testReadAllSinFiltro() {
		when(inventarioRepository.findByActivoTrue()).thenReturn(Arrays.asList(inventarioEjemplo));

		List<InventarioResponse> resultado = inventarioService.readAll(null);

		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		assertEquals(10, resultado.get(0).getIdInventario());
		assertEquals("Laptop", resultado.get(0).getProducto().getNombreProducto());
		verify(inventarioRepository).findByActivoTrue();
	}

	@Test
	@DisplayName("ReadAll con filtro retorna elementos filtrados")
	void testReadAllConFiltro() {
		when(inventarioRepository.findByProductosNombreProductoContainingIgnoreCaseAndActivoTrue("Lap"))
				.thenReturn(Arrays.asList(inventarioEjemplo));

		List<InventarioResponse> resultado = inventarioService.readAll("Lap");

		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		verify(inventarioRepository).findByProductosNombreProductoContainingIgnoreCaseAndActivoTrue("Lap");
	}

	@Test
	@DisplayName("ReabById con ID existente retorna el Inventario")
	void testReabByIdExistente() {
		when(inventarioRepository.findById(10)).thenReturn(Optional.of(inventarioEjemplo));

		Inventario resultado = inventarioService.reabById(10);

		assertNotNull(resultado);
		assertEquals(10, resultado.getIdInventario());
	}

	@Test
	@DisplayName("ReabById con ID inexistente retorna null")
	void testReabByIdInexistente() {
		when(inventarioRepository.findById(99)).thenReturn(Optional.empty());

		Inventario resultado = inventarioService.reabById(99);

		assertNull(resultado);
	}

	@Test
	@DisplayName("Create guarda y retorna nuevo registro de Inventario")
	void testCreateExitoso() {
		InventarioDTO dto = new InventarioDTO();
		dto.setIdProducto(1);

		when(productosRepository.findById(1)).thenReturn(Optional.of(productoEjemplo));
		when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioEjemplo);

		Inventario resultado = inventarioService.create(dto);

		assertNotNull(resultado);
		assertEquals(10, resultado.getIdInventario());
		verify(inventarioRepository).save(any(Inventario.class));
	}

	@Test
	@DisplayName("Create lanza excepción cuando el Producto no existe")
	void testCreateProductoNoEncontrado() {
		InventarioDTO dto = new InventarioDTO();
		dto.setIdProducto(99);

		when(productosRepository.findById(99)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> inventarioService.create(dto));
	}

	@Test
	@DisplayName("UpdateById actualiza exitosamente el Inventario")
	void testUpdateByIdExitoso() {
		InventarioDTO dto = new InventarioDTO();
		dto.setIdProducto(1);
		dto.setStock(20);

		when(inventarioRepository.findById(10)).thenReturn(Optional.of(inventarioEjemplo));
		when(productosRepository.findById(1)).thenReturn(Optional.of(productoEjemplo));
		when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioEjemplo);

		Inventario resultado = inventarioService.updateById(10, dto);

		assertNotNull(resultado);
		verify(inventarioRepository).save(any(Inventario.class));
	}

	@Test
	@DisplayName("DeleteLogico cambia estado activo a false")
	void testDeleteLogicoExitoso() {
		when(inventarioRepository.findById(10)).thenReturn(Optional.of(inventarioEjemplo));
		when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioEjemplo);

		inventarioService.deleteLogico(10);

		assertFalse(inventarioEjemplo.getActivo());
		verify(inventarioRepository).save(inventarioEjemplo);
	}

	@Test
	@DisplayName("DeleteLogico lanza excepción cuando ID no existe")
	void testDeleteLogicoNoEncontrado() {
		when(inventarioRepository.findById(99)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> inventarioService.deleteLogico(99));
	}
}
