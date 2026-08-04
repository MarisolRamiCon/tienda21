package com.inndata21.tienda21.service;

import java.util.List;
import com.inndata21.tienda21.entity.pedido;


public interface IPedido {
    public List<pedido> leerTodo();
    public pedido readById(Integer idPedido);
    public pedido create(pedido pedido);
    public pedido update(Integer idPedido, pedido pedido);
    public String delete(Integer idPedido);
    //borrado logico
    //public String deleteLogico(Integer idDetallePedido);

}