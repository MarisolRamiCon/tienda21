package com.inndata21.tienda21.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inndata21.tienda21.dto.ProveedorDTO;
import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.entity.Proveedores;
import com.inndata21.tienda21.repository.ProductosRepository;
import com.inndata21.tienda21.repository.ProveedoresFeignRepository;
import com.inndata21.tienda21.repository.ProveedoresRepository;
import com.inndata21.tienda21.service.impl.ProveedoresService;

import response.ProveedorConProductosResponse;
import response.ProveedoresResponse;

@ExtendWith(MockitoExtension.class)
class ProveedoresServiceTest {

	@Mock
	private ProveedoresRepository proveedoresRepository;

	@Mock
	private ProductosRepository productosRepository;

	@Mock
	private ProveedoresFeignRepository proveedoresFeignRepository;

	@InjectMocks
	private ProveedoresService proveedoresService;

	private Proveedores proveedorEjemplo;
	private Productos productoEjemplo;

	@BeforeEach
	void setUp() {
		proveedorEjemplo = new Proveedores();
		proveedorEjemplo.setIdProveedor(1);
		proveedorEjemplo.setNombreEmpresa("TechCorp");
		proveedorEjemplo.setContacto("Carlos Garcia");
		proveedorEjemplo.setCorreoElectronico("contacto@techcorp.com");
		proveedorEjemplo.setTelefono("555123456");
		proveedorEjemplo.setActivo(true);

		productoEjemplo = new Productos();
		productoEjemplo.setIdProducto(10);
		productoEjemplo.setNombreProducto("Monitor 27'");
		productoEjemplo.setProveedorId(1);
		productoEjemplo.setActive(true);
	}

	@Test
	@DisplayName("ReadAll sin filtro retorna lista de ProveedoresResponse")
	void testReadAllSinFiltro() {
		when(proveedoresRepository.findByActivoTrue()).thenReturn(Arrays.asList(proveedorEjemplo));

		List<ProveedoresResponse> resultado = proveedoresService.readAll(null);

		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		assertEquals("TechCorp", resultado.get(0).getNombreEmpresa());
		verify(proveedoresRepository).findByActivoTrue();
	}

	@Test
	@DisplayName("ReadAll con filtro busca proveedores por nombre de empresa")
	void testReadAllConFiltro() {
		when(proveedoresRepository.findByNombreEmpresaContainingIgnoreCaseAndActivoTrue("Tech"))
				.thenReturn(Arrays.asList(proveedorEjemplo));

		List<ProveedoresResponse> resultado = proveedoresService.readAll("Tech");

		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		verify(proveedoresRepository).findByNombreEmpresaContainingIgnoreCaseAndActivoTrue("Tech");
	}

	@Test
	@DisplayName("ReadById retorna ProveedoresResponse si se encuentra activo")
	void testReadByIdExitoso() {
		when(proveedoresRepository.findByIdAndActivoTrue(1)).thenReturn(Optional.of(proveedorEjemplo));

		ProveedoresResponse resultado = proveedoresService.readById(1);

		assertNotNull(resultado);
		assertEquals(1, resultado.getIdProveedor());
		assertEquals("TechCorp", resultado.getNombreEmpresa());
	}

	@Test
	@DisplayName("Create registra un nuevo proveedor y retorna ProveedoresResponse")
	void testCreateExitoso() {
		ProveedorDTO dto = new ProveedorDTO();
		dto.setNombreEmpresa("NewTech");
		dto.setContacto("Ana Lopez");
		dto.setCorreoElectronico("ana@newtech.com");
		dto.setTelefono("555987654");

		when(proveedoresRepository.save(any(Proveedores.class))).thenReturn(proveedorEjemplo);

		ProveedoresResponse resultado = proveedoresService.create(dto);

		assertNotNull(resultado);
		verify(proveedoresRepository).save(any(Proveedores.class));
	}

	@Test
	@DisplayName("UpdateById actualiza campos y retorna ProveedoresResponse")
	void testUpdateByIdExitoso() {
		ProveedorDTO dto = new ProveedorDTO();
		dto.setNombreEmpresa("TechCorp Updated");
		dto.setContacto("Carlos Garcia");
		dto.setCorreoElectronico("contacto@techcorp.com");
		dto.setTelefono("555123456");

		when(proveedoresRepository.findById(1)).thenReturn(Optional.of(proveedorEjemplo));
		when(proveedoresRepository.save(any(Proveedores.class))).thenReturn(proveedorEjemplo);

		ProveedoresResponse resultado = proveedoresService.updateById(1, dto);

		assertNotNull(resultado);
		verify(proveedoresRepository).save(any(Proveedores.class));
	}

	@Test
	@DisplayName("DeleteLogico establece el atributo activo en false")
	void testDeleteLogicoExitoso() {
		when(proveedoresRepository.findById(1)).thenReturn(Optional.of(proveedorEjemplo));
		when(proveedoresRepository.save(any(Proveedores.class))).thenReturn(proveedorEjemplo);

		proveedoresService.deleteLogico(1);

		assertFalse(proveedorEjemplo.getActivo());
		verify(proveedoresRepository).save(proveedorEjemplo);
	}

	@Test
	@DisplayName("ObtenerProveedorConProductos retorna ProveedorConProductosResponse con productos")
	void testObtenerProveedorConProductos() {
		when(proveedoresRepository.findByIdAndActivoTrue(1)).thenReturn(Optional.of(proveedorEjemplo));
		when(productosRepository.findProductosActivosPorProveedor(1)).thenReturn(Arrays.asList(productoEjemplo));

		ProveedorConProductosResponse resultado = proveedoresService.obtenerProveedorConProductos(1);

		assertNotNull(resultado);
		assertEquals("TechCorp", resultado.getProveedor().getNombreEmpresa());
		assertEquals(1, resultado.getProductos().size());
		assertEquals("Monitor 27'", resultado.getProductos().get(0).getNombreProducto());
	}

	@Test
	@DisplayName("ObtenerProveedorPorFeign consume ProveedoresFeignRepository")
	void testObtenerProveedorPorFeign() {
		ProveedoresResponse respFeign = new ProveedoresResponse();
		respFeign.setIdProveedor(1);
		respFeign.setNombreEmpresa("TechCorp Feign");

		when(proveedoresFeignRepository.obtenerProveedorPorId(1)).thenReturn(respFeign);

		ProveedoresResponse resultado = proveedoresService.obtenerProveedorPorFeign(1);

		assertNotNull(resultado);
		assertEquals("TechCorp Feign", resultado.getNombreEmpresa());
		verify(proveedoresFeignRepository).obtenerProveedorPorId(1);
	}
}
