package com.inndata21.tienda21.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inndata21.tienda21.dto.APITerceros.Viajes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inndata21.tienda21.service.IViajesService;

@RestController
@RequestMapping("/api/v1")

public class ViajesController {
    @Autowired
    private IViajesService viajesService;

    @GetMapping("/viajes")
    public List<Viajes> readAll() {
        return viajesService.readAll();
    }

    @GetMapping("/viajes/{id}")
    public Viajes readById(@PathVariable Integer id) {
        return viajesService.readById(id);
    }

    @PostMapping("/viajes")
    public Viajes create(@RequestBody Viajes viajes) {
        return viajesService.create(viajes);
    }
}
