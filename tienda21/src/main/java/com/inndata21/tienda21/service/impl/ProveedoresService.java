package com.inndata21.tienda21.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.dto.ProveedorDTO;
import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.entity.Proveedores;
import com.inndata21.tienda21.repository.ProductosRepository;
import com.inndata21.tienda21.repository.ProveedoresFeignRepository;
import com.inndata21.tienda21.repository.ProveedoresRepository;
import com.inndata21.tienda21.service.IProveedoresService;

import response.ProductosResponse;
import response.ProveedorConProductosResponse;
import response.ProveedoresResponse;

@Service
public class ProveedoresService implements IProveedoresService {

	@Autowired
	private ProveedoresRepository _proveedoresRepository;

	@Autowired
	private ProductosRepository _productosRepository;

	@Autowired
	private ProveedoresFeignRepository _proveedoresFeignRepository;

	@Override
	public List<ProveedoresResponse> readAll(String filtro) {
		List<Proveedores> lista = (filtro != null && !filtro.isBlank())
				? _proveedoresRepository.findByNombreEmpresaContainingIgnoreCaseAndActivoTrue(filtro)
				: _proveedoresRepository.findByActivoTrue();

		return lista.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public ProveedoresResponse readById(Integer id) {
		Proveedores proveedor = _proveedoresRepository.findByIdAndActivoTrue(id).orElse(null);
		return mapToResponse(proveedor);
	}

	@Override
	public ProveedoresResponse create(ProveedorDTO dto) {
		Proveedores proveedor = new Proveedores();
		proveedor.setNombreEmpresa(dto.getNombreEmpresa());
		proveedor.setContacto(dto.getContacto());
		proveedor.setCorreoElectronico(dto.getCorreoElectronico());
		proveedor.setTelefono(dto.getTelefono());
		proveedor.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

		Proveedores guardado = _proveedoresRepository.save(proveedor);
		return mapToResponse(guardado);
	}

	@Override
	public ProveedoresResponse updateById(Integer id, ProveedorDTO dto) {
		Optional<Proveedores> proveedorExistente = _proveedoresRepository.findById(id);
		if (proveedorExistente.isPresent()) {
			Proveedores p = proveedorExistente.get();
			p.setNombreEmpresa(dto.getNombreEmpresa());
			p.setContacto(dto.getContacto());
			p.setCorreoElectronico(dto.getCorreoElectronico());
			p.setTelefono(dto.getTelefono());
			if (dto.getActivo() != null) {
				p.setActivo(dto.getActivo());
			}

			Proveedores actualizado = _proveedoresRepository.save(p);
			return mapToResponse(actualizado);
		}
		return null;
	}

	@Override
	public void deleteLogico(Integer id) {
		Proveedores proveedor = _proveedoresRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + id));

		proveedor.setActivo(false);
		_proveedoresRepository.save(proveedor);
	}

	@Override
	public ProveedorConProductosResponse obtenerProveedorConProductos(Integer idProveedor) {
		Proveedores proveedor = _proveedoresRepository.findByIdAndActivoTrue(idProveedor)
				.orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + idProveedor));

		List<Productos> productos = _productosRepository.findProductosActivosPorProveedor(idProveedor);

		List<ProductosResponse> productosResponseList = productos.stream().map(p -> {
			ProductosResponse pr = new ProductosResponse();
			pr.setNombreProducto(p.getNombreProducto());
			pr.setPrecio(p.getPrecio());
			pr.setStock(p.getStock());
			return pr;
		}).collect(Collectors.toList());

		ProveedorConProductosResponse response = new ProveedorConProductosResponse();
		response.setProveedor(mapToResponse(proveedor));
		response.setProductos(productosResponseList);

		return response;
	}

	@Override
	public ProveedoresResponse obtenerProveedorPorFeign(Integer idProveedor) {
		return _proveedoresFeignRepository.obtenerProveedorPorId(idProveedor);
	}

	private ProveedoresResponse mapToResponse(Proveedores entidad) {
		if (entidad == null) {
			return null;
		}
		ProveedoresResponse response = new ProveedoresResponse();
		response.setIdProveedor(entidad.getIdProveedor());
		response.setNombreEmpresa(entidad.getNombreEmpresa());
		response.setContacto(entidad.getContacto());
		response.setCorreoElectronico(entidad.getCorreoElectronico());
		response.setTelefono(entidad.getTelefono());
		response.setActivo(entidad.getActivo());
		return response;
	}
}
