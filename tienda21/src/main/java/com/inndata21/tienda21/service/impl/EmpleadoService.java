package com.inndata21.tienda21.service.impl;

import com.inndata21.tienda21.entity.Empleado;
import com.inndata21.tienda21.repository.EmpleadoRepository;
import com.inndata21.tienda21.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Override
    public List<Empleado> readAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado readById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado create(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateById(Integer id, Empleado empleado) {
        Optional<Empleado> empleadoActualizar = empleadoRepository.findById(id);
        if (empleadoActualizar.isPresent()) {
            Empleado empleado2 = empleadoActualizar.get();
            empleado2.setNombre(empleado.getNombre());
            empleado2.setApellido(empleado.getApellido());
            empleado2.setPuesto(empleado.getPuesto());
            empleado2.setSalario(empleado.getSalario());
            empleado2.setFechaContratacion(empleado.getFechaContratacion());
            empleadoRepository.save(empleado2);

            return empleado2;
        } else {
            return new Empleado();
        }
    }
}
