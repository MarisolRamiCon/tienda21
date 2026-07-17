package com.inndata21.tienda21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inndata21.tienda21.entity.DetallePedido;
import com.inndata21.tienda21.service.impl.DetallePedidoService;

@RestController
@RequestMapping("/api/v1")
public class DetallePedidoController {
    @Autowired
    DetallePedidoService detallePedidoService;
    @GetMapping("/ticket")
    public List<DetallePedido> readAll(){
        return detallePedidoService.readAll();
    }
    
}
