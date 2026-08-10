package com.inndata21.tienda21.service;

import java.util.List;

import com.inndata21.tienda21.dto.ProveedorDTO;

import response.ProveedorConProductosResponse;
import response.ProveedoresResponse;

public interface IProveedoresService {

	List<ProveedoresResponse> readAll(String filtro);

	ProveedoresResponse readById(Integer id);

	ProveedoresResponse create(ProveedorDTO proveedorDTO);

	ProveedoresResponse updateById(Integer id, ProveedorDTO proveedorDTO);

	void deleteLogico(Integer id);

	ProveedorConProductosResponse obtenerProveedorConProductos(Integer idProveedor);

	ProveedoresResponse obtenerProveedorPorFeign(Integer idProveedor);
}
