package com.inndata21.tienda21.service;

import java.util.List;

import com.inndata21.tienda21.entity.DetallePedido;
import com.inndata21.tienda21.dto.response.DetallePedidoResponse;
import com.inndata21.tienda21.dto.request.DetallePedidoRequest;

public interface IDetallePedidoService {
    public List<DetallePedido> readAll();   
    public DetallePedido readById(Integer idDetallePedido);
    public DetallePedido create(DetallePedido detallePedido);
    public DetallePedido update(Integer idDetallePedido, DetallePedido detallePedido);
    public String delete(Integer idDetallePedido);
    
    public String deleteLogico(Integer idDetallePedido);

    public List<DetallePedidoResponse> detallePedidoByPedidoId(Integer pedidoId);
    public DetallePedidoResponse createDetallePedidoDto(DetallePedidoRequest detallePedidoRequest);

    public List<DetallePedido> detallePedidoByProductoId(Integer productoId);
}
