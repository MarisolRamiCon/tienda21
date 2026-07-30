package com.tienda.inndata021.service.implemetacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.inndata021.dto.pedidoResponse;
import com.tienda.inndata021.entity.pedido;
import com.tienda.inndata021.repository.PedidoRepository;
import com.tienda.inndata021.service.IPedido;


@Service 
public class pedidoService implements IPedido{
    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<pedido> leerTodo(){
        // return pedidoRepository.findAll().stream().map(pedido -> new pedidoResponse(
        //     pedido.getIdPedido(),
        //     pedido.getFecha_pedido(),
        //     pedido.getCliente_id(),
        //     pedido.getTotal_pedido()
        // )).toList();
        return pedidoRepository.findAll();
    }

    @Override
    public pedido readById(Integer idPedido) {
        return pedidoRepository.findById(idPedido).orElse(null);
    }

    @Override
    public pedido create(pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public pedido update(Integer idPedido, pedido pedido) {
         Optional<pedido> pedidoOptional = pedidoRepository.findById(idPedido);
         if (pedidoOptional.isPresent()) {
             pedido existingPedido = pedidoOptional.get();
             existingPedido.setFecha_pedido(pedido.getFecha_pedido());
             existingPedido.setCliente_id(pedido.getCliente_id());
             existingPedido.setTotal_pedido(pedido.getTotal_pedido());
             return pedidoRepository.save(existingPedido);
         } else {
             return new pedido();
         }
    }

    @Override
    public String delete(Integer idPedido) {
        Optional<pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        if (pedidoOptional.isPresent()) {
            pedidoRepository.deleteById(idPedido);
            return "Pedido eliminado correctamente.";
        } else {
            return "Pedido no encontrado.";
        }
    }

    // @Override
    //     public String deleteLogico(Integer idDetallePedido) {
    //         Optional<detallePedido> detallePedidoOptional = detallePedidoRepository.findById(idDetallePedido);
    //         if (detallePedidoOptional.isPresent()) {
    //             detallePedido existingDetallePedido = detallePedidoOptional.get();
    //             existingDetallePedido.setIsActive(false);
    //             detallePedidoRepository.save(existingDetallePedido);
    //             return "Detalle de pedido eliminado lógicamente.";
    //         } else {
    //             return "Detalle de pedido no encontrado.";
    //         }
    //     }

}