package com.inndata21.tienda21.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inndata21.tienda21.entity.pedido;


public interface PedidoRepository extends JpaRepository<pedido, Integer> {
}