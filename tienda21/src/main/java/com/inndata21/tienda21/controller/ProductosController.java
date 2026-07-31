package com.inndata21.tienda21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inndata21.tienda21.entity.Productos;
import com.inndata21.tienda21.service.impl.ProductosService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class ProductosController {

    @Autowired
    ProductosService productosService;
 
    @GetMapping("/productos")
    public List<Productos> readAll(){
        return productosService.readAll();
    }

    @GetMapping("/productos/{id}")
    public Productos readById(@PathVariable Integer id){
        return productosService.reabById(id);
    }

    @PostMapping("/productos")
    public Productos create(@RequestBody Productos producto){
        return productosService.create(producto);
    }

    @PutMapping("/productos/{id}")
    public Productos updateById(@PathVariable Integer id, @RequestBody Productos producto){
        return productosService.updateById(id, producto);
    }
}
