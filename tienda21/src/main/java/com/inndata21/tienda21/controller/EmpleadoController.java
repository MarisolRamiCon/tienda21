package com.inndata21.tienda21.controller;

import com.inndata21.tienda21.entity.Empleado;
import com.inndata21.tienda21.service.impl.EmpleadoService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @GetMapping("/empleados")
    public List<Empleado> readAll() {
        return empleadoService.readAll();
    }

    @GetMapping("/empleados/{id}")
    public Empleado readByID(@PathVariable Integer id) {
        return empleadoService.readById(id);
    }

    @PostMapping("/empleados")
    public Empleado create(@RequestBody Empleado empleado) {
        return empleadoService.create(empleado);
    }

    @PutMapping("/empleados")
    public Empleado updateById(@Param("id")Integer id, @RequestBody Empleado empleado) {
        return empleadoService.updateById(id, empleado);
    }
}
