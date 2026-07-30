package com.tienda.inndata021.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.inndata021.entity.productos;

public interface ProductosRespository extends JpaRepository<productos, Integer> {
}