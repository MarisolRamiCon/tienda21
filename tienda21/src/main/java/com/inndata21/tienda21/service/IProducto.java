package com.inndata21.tienda21.service;

import java.util.List;

import com.inndata21.tienda21.dto.response.ProductosResponse;
import com.inndata21.tienda21.entity.productos;



public interface IProducto {
    public List<productos> leerTodo();
    public productos readById(Integer idProducto);
    public productos create(productos prductos);
    public productos update(Integer idProducto, productos prductos);
    public String delete(Integer idProducto);
    //borrado logico
    public String deleteLogico(Integer idProducto);
    //borrado fisico
    public String deleteFisico(Integer idProducto); 

    //metodos personalizados con dto
    public List<ProductosResponse> productosBaratos(Double Precio);
    public List<ProductosResponse> productosProveedorStock(Integer proveedorId, Integer stock);
    
    //metodo con queries
    public List<productos> productosBaratosQuery(Double Precio);
}