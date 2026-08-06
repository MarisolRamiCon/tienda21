package com.inndata21.tienda21.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.entity.DetallePedido;
import com.inndata21.tienda21.repository.DetallePedidoRepository;
import com.inndata21.tienda21.service.IDetallePedidoService;
import com.inndata21.tienda21.dto.response.DetallePedidoResponse;
import com.inndata21.tienda21.dto.request.DetallePedidoRequest;
@Service
public class DetallePedidoService implements IDetallePedidoService{
    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<DetallePedido> readAll() {
        return detallePedidoRepository.findAll();
    }
    
    @Override
    public DetallePedido readById(Integer idDetallePedido) {
        return detallePedidoRepository.findById(idDetallePedido).orElse(null);
    }

    @Override
    public DetallePedido create(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public DetallePedido update(Integer idDetallePedido, DetallePedido detallePedido) {
        DetallePedido existingDetallePedido = detallePedidoRepository.findById(idDetallePedido).orElse(null);
        if (existingDetallePedido != null) {
            existingDetallePedido.setCantidad(detallePedido.getCantidad());
            existingDetallePedido.setPrecio(detallePedido.getPrecio());
            existingDetallePedido.setProducto(detallePedido.getProducto());
            existingDetallePedido.setPedido(detallePedido.getPedido());
            return detallePedidoRepository.save(existingDetallePedido);
        }
        return null;
    }

    @Override
    public String delete(Integer idDetallePedido) {
        detallePedidoRepository.deleteById(idDetallePedido);
        return "DetallePedido eliminado correctamente";
    }

    @Override
    public String deleteLogico(Integer idDetallePedido) {
        DetallePedido existingDetallePedido = detallePedidoRepository.findById(idDetallePedido).orElse(null);
        if (existingDetallePedido != null) {
            existingDetallePedido.setActive(false);
            detallePedidoRepository.save(existingDetallePedido);
            return "DetallePedido eliminado lógicamente correctamente";
        }
        return "DetallePedido no encontrado";
    }

    @Override
    public List<DetallePedido> detallePedidoByProductoId(Integer productoId) {
        return detallePedidoRepository.findByProductoId(productoId);
    }

    @Override
    public List<DetallePedido> detallePedidoByPedidoId(Integer pedidoId) {
        return detallePedidoRepository.findByPedidoId(pedidoId);
    }

    @Override
    public DetallePedidoResponse createDetallePedidoDto(DetallePedidoRequest detallePedidoRequest) {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(detallePedidoRequest.getCantidad());
        detallePedido.setPrecio(detallePedidoRequest.getPrecio());
        detallePedido.setProducto(detallePedidoRequest.getProducto());
        detallePedido.setPedido(detallePedidoRequest.getPedido());
        DetallePedido savedDetallePedido = detallePedidoRepository.save(detallePedido);
        
        DetallePedidoResponse response = new DetallePedidoResponse();
        response.setIdDetallePedido(savedDetallePedido.getIdDetallePedido());
        response.setCantidad(savedDetallePedido.getCantidad());
        response.setPrecio(savedDetallePedido.getPrecio());
        response.setProducto(savedDetallePedido.getProducto());
        response.setPedido(savedDetallePedido.getPedido());
        
        return response;
    }
}
