package com.inndata21.tienda21.service;

import java.util.List;

import com.inndata21.tienda21.dto.InventarioDTO;
import com.inndata21.tienda21.entity.Inventario;

import response.InventarioResponse;

public interface IInventarioService {
	public List<InventarioResponse> readAll(String filtro);
    public Inventario reabById(Integer id);
    public Inventario create(InventarioDTO inventario);
    public Inventario updateById(Integer id, InventarioDTO inventario);
}
