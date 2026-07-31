package com.inndata21.tienda21.service;

import com.inndata21.tienda21.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {
    public List<Empleado> readAll();
    public Empleado readById(Integer id);
    public Empleado create(Empleado empleado);
    public Empleado updateById(Integer id, Empleado empleado);
    // Borrado físico/total
    //public String deleteById(Integer id);
    // Borrado lógico/parcial
    //public Boolean changeAvailability(Integer id);
}
