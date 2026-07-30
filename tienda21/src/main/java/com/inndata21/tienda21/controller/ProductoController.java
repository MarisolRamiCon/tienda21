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

import com.tienda.inndata021.dto.ProductoResponse;
import com.tienda.inndata021.entity.detallePedido;
import com.tienda.inndata021.entity.productos;
import com.tienda.inndata021.service.implemetacion.ProductoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/Producto")
    public List<ProductoResponse> leerTodo(){
        return productoService.leerTodo();
    }

    @GetMapping("/Producto/{idProducto}")
    public productos readById(@PathVariable Integer idProducto){
        return productoService.readById(idProducto);
    }

    @PostMapping("/Producto")
    public productos create(@RequestBody productos producto){
        return productoService.create(producto);
    }

    @PutMapping("/Producto/{idProducto}")
    public productos update(@PathVariable Integer idProducto, @RequestBody productos producto){
        return productoService.update(idProducto, producto);
    }

    @DeleteMapping("/Producto/{idProducto}")
    public String delete(@PathVariable Integer idProducto){
        return productoService.delete(idProducto);
    }
}