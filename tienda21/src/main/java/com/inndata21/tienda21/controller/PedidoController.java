package com.tienda.inndata021.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.inndata021.dto.pedidoResponse;
import com.tienda.inndata021.entity.pedido;
import com.tienda.inndata021.service.implemetacion.pedidoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class PedidoController {
    @Autowired
    pedidoService pedidoService;
    
    @GetMapping("/pedido")
    public List<pedidoResponse> leerTodo(){
        return pedidoService.leerTodo();
    }

    @GetMapping("/pedido/{idPedido}")
    public pedido readById(@PathVariable Integer idPedido){
        return pedidoService.readById(idPedido);
    }

    @PostMapping("/pedido")
    public pedido create(@RequestBody pedido pedido){
        return pedidoService.create(pedido);
    }

    @PutMapping("/pedido/{idPedido}")
    public pedido update(@PathVariable Integer idPedido, @RequestBody pedido pedido){
        return pedidoService.update(idPedido, pedido);
    }

    @DeleteMapping("/pedido/{idPedido}")
    public String delete(@PathVariable Integer idPedido){
        return pedidoService.delete(idPedido);
    }
}