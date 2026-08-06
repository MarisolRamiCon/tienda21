package com.inndata21.tienda21.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.dto.request.PedidoRequest;
import com.inndata21.tienda21.dto.response.PedidoResponse;
import com.inndata21.tienda21.entity.pedido;
import com.inndata21.tienda21.repository.PedidoRepository;
import com.inndata21.tienda21.service.IPedido;


@Service 
public class pedidoService implements IPedido{
    @Autowired
    PedidoRepository pedidoRepository;

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

    @Override
    public List<pedido> leerTodo() {
        return pedidoRepository.findAll();
    }
    //dtos
    @Override
    public String deleteLogico(Integer idPedido) {
        Optional<pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        if (pedidoOptional.isPresent()) {
            pedido pedido = pedidoOptional.get();
            pedido.setActive(false);
            pedidoRepository.save(pedido);
            return "Pedido eliminado lógicamente correctamente.";
        } else {
            return "Pedido no encontrado.";
        }
    }

    @Override
    public List<PedidoResponse> pedidosPorCliente(Integer clienteId) {
        List<pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
        return pedidos.stream().map(
            pedido -> {
                PedidoResponse pedidoResponse = new PedidoResponse();
                pedidoResponse.setFecha_pedido(pedido.getFecha_pedido());
                pedidoResponse.setCliente_id(pedido.getCliente_id());
                pedidoResponse.setTotal_pedido(pedido.getTotal_pedido());
                return pedidoResponse;
            }
        ).toList();
    }

    @Override
    public List<PedidoResponse> pedidosPorFecha(String fecha_pedido) {
        List<pedido> pedidos = pedidoRepository.findAll(); // Aquí deberías implementar la lógica para filtrar por fecha
        return pedidos.stream().map(
            pedido -> {
                PedidoResponse pedidoResponse = new PedidoResponse();
                pedidoResponse.setFecha_pedido(pedido.getFecha_pedido());
                pedidoResponse.setCliente_id(pedido.getCliente_id());
                pedidoResponse.setTotal_pedido(pedido.getTotal_pedido());
                return pedidoResponse;
            }
        ).toList();
    }

    @Override
    public PedidoResponse createPedidoDto(PedidoRequest pedidoRequest) {
        pedido pedido = new pedido();
        pedido.setFecha_pedido(pedidoRequest.getFecha_pedido());
        pedido.setCliente_id(pedidoRequest.getCliente_id());
        pedido.setTotal_pedido(pedidoRequest.getTotal_pedido());
        pedidoRepository.save(pedido);

        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setFecha_pedido(pedido.getFecha_pedido());
        pedidoResponse.setCliente_id(pedido.getCliente_id());
        pedidoResponse.setTotal_pedido(pedido.getTotal_pedido());

        return pedidoResponse;
    }

    @Override
    public List<pedido> pedidosPorClienteQuery(Integer clienteId) {
        return pedidoRepository.findPedidosActivosByClienteId(clienteId);
    }


}