package com.inndata21.tienda21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inndata21.tienda21.entity.productos;

public interface ProductosRespository extends JpaRepository<productos, Integer> {
//metodos personalizados
    public List<productos> findByPrecioLessThan(Double Precio);
    
}