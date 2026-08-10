package com.inndata21.tienda21.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inndata21.tienda21.entity.Proveedores;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {

	List<Proveedores> findByActivoTrue();

	List<Proveedores> findByNombreEmpresaContainingIgnoreCaseAndActivoTrue(String nombreEmpresa);

	@Query("SELECT p FROM Proveedores p WHERE p.idProveedor = :idProveedor AND p.activo = true")
	Optional<Proveedores> findByIdAndActivoTrue(@Param("idProveedor") Integer idProveedor);
}
