package com.inndata21.tienda21.controller;
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

import com.inndata21.tienda21.entity.DetallePedido;
import com.inndata21.tienda21.entity.productos;
import com.inndata21.tienda21.service.impl.ProductoService;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestParam;

import com.inndata21.tienda21.dto.response.ProductosResponse;


@RestController
@RequestMapping("/api/v1")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/Producto")
    public List<productos> leerTodo(){
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

    //metodos personalizados
    @GetMapping("/productosBaratos")
    public List<ProductosResponse> productosBaratos(@PathParam("precio") Double precio) {
        return productoService.productosBaratos(precio);
    }
    
}