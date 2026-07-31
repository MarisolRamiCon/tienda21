package com.inndata21.tienda21.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inndata21.tienda21.entity.Inventario;
import com.inndata21.tienda21.entity.Productos;


public interface InventarioRepository extends JpaRepository<Inventario,Integer> {

}
