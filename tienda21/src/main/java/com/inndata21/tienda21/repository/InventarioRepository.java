package com.inndata21.tienda21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inndata21.tienda21.entity.Inventario;
import com.inndata21.tienda21.entity.Productos;

import feign.Param;


public interface InventarioRepository extends JpaRepository<Inventario,Integer> {

	List<Inventario> findByActivoTrue();
	List<Inventario> findByProductosNombreProductoContainingIgnoreCaseAndActivoTrue(String nombreProducto);

    @Query("SELECT i FROM Inventario i WHERE i.stock < :stockMinimo AND i.activo = true")
    List<Inventario> obtenerInventarioConBajoStock(@Param("stockMinimo") Integer stockMinimo);
}
