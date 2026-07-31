package com.inndata21.tienda21.controller;

import com.inndata21.tienda21.entity.Cliente;
import com.inndata21.tienda21.service.impl.ClienteService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @GetMapping("/personas")
    public List<Cliente> readAll() {
        return clienteService.readAll();
    }

    @GetMapping("/personas/{id}")
    public Cliente readByID(@PathVariable Integer id) {
        return clienteService.readById(id);
    }

    @PostMapping("/clientes")
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.create(cliente);
    }

    @PutMapping("/clientes")
    public Cliente updateById(@Param("id")Integer id, @RequestBody Cliente cliente) {
        return clienteService.updateById(id, cliente);
    }
}
