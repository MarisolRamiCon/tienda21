package com.tienda.inndata021.service;

import java.util.List;
import com.tienda.inndata021.dto.pedidoResponse;
import com.tienda.inndata021.entity.pedido;


public interface IPedido {
    public List<pedidoResponse> leerTodo();
    public pedido readById(Integer idPedido);
    public pedido create(pedido pedido);
    public pedido update(Integer idPedido, pedido pedido);
    public String delete(Integer idPedido);
    //borrado logico
    //public String deleteLogico(Integer idDetallePedido);

}