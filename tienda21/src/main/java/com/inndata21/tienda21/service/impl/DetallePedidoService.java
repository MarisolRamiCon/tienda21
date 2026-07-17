package com.inndata21.tienda21.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata21.tienda21.entity.DetallePedido;
import com.inndata21.tienda21.repository.DetallePedidoRepository;
import com.inndata21.tienda21.service.IDetallePedidoService;
@Service
public class DetallePedidoService implements IDetallePedidoService{
    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<DetallePedido> readAll() {
        return detallePedidoRepository.findAll();

    }
    
}
