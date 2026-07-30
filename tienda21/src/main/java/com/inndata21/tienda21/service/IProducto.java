package com.tienda.inndata021.service;

import java.util.List;
import com.tienda.inndata021.dto.ProductoResponse;
import com.tienda.inndata021.entity.productos;


public interface IProducto {
    public List<ProductoResponse> leerTodo();
    public productos readById(Integer idProducto);
    public productos create(productos prductos);
    public productos update(Integer idProducto, productos prductos);
    public String delete(Integer idProducto);
    //borrado logico
    //public String deleteLogico(Integer idDetallePedido);

}