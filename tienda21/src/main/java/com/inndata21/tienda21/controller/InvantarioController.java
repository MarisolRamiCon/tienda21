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

import com.inndata21.tienda21.dto.InventarioDTO;
import com.inndata21.tienda21.entity.Inventario;
import com.inndata21.tienda21.service.impl.InventarioService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class InvantarioController {
    @Autowired
    InventarioService inventarioService;

    
    @GetMapping("/inventario")
    public List<Inventario> readAll(){
        return inventarioService.readAll();
    }

    @GetMapping("/inventario/{id}")
    public Inventario readById(@PathVariable Integer id){
        return inventarioService.reabById(id);
    }

    @PostMapping("/inventario")
    public Inventario create(@RequestBody InventarioDTO inventario){
        return inventarioService.create(inventario);
    }

    @PutMapping("/inventario")
    public Inventario updateById(@PathParam("id") Integer id, @RequestBody InventarioDTO inventario){
        return inventarioService.updateById(id, inventario);
    }
}
