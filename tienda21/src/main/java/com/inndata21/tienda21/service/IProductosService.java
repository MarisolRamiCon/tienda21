package com.inndata21.tienda21.service;

import java.util.List;

import com.inndata21.tienda21.entity.Productos;

public interface IProductosService {
    public List<Productos> readAll();
    public Productos reabById(Integer id);
    public Productos create(Productos producto);
    public Productos updateById(Integer id, Productos producto);
}
