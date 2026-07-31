package com.inndata21.tienda21.service.impl;

import com.inndata21.tienda21.entity.Cliente;
import com.inndata21.tienda21.repository.ClienteRepository;
import com.inndata21.tienda21.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public List<Cliente> readAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente readById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateById(Integer id, Cliente cliente) {
        Optional<Cliente> clienteActualizar = clienteRepository.findById(id);
        if (clienteActualizar.isPresent()) {
            Cliente cliente2 = clienteActualizar.get();
            cliente2.setNombre(cliente.getNombre());
            cliente2.setApellido(cliente.getApellido());
            cliente2.setDireccion(cliente.getDireccion());
            cliente2.setCorreo(cliente.getCorreo());
            cliente2.setTelefono(cliente.getTelefono());
            clienteRepository.save(cliente2);

            return cliente2;
        } else {
            return new Cliente();
        }
    }
}
