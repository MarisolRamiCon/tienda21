package com.inndata21.tienda21.service;

import com.inndata21.tienda21.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> readAll();
    public Cliente readById(Integer id);
    public Cliente create(Cliente cliente);
    public Cliente updateById(Integer id, Cliente cliente);
    // Borrado físico/total
    //public String deleteById(Integer id);
    // Borrado lógico/parcial
    //public Boolean changeAvailability(Integer id);
}
