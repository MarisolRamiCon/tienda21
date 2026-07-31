package com.inndata21.tienda21.repository;

import com.inndata21.tienda21.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
