package com.inndata21.tienda21.repository;

import com.inndata21.tienda21.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
