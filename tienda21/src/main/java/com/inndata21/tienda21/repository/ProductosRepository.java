package com.inndata21.tienda21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inndata21.tienda21.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {

	List<Productos> findByProveedorId(Integer proveedorId);

	@Query("SELECT p FROM Productos p WHERE p.proveedorId = :proveedorId AND p.active = true")
	List<Productos> findProductosActivosPorProveedor(@Param("proveedorId") Integer proveedorId);
}

