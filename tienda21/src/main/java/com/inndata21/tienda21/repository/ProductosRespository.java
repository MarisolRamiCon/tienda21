package com.inndata21.tienda21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inndata21.tienda21.entity.productos;

public interface ProductosRespository extends JpaRepository<productos, Integer> {
//metodos personalizados
    public List<productos> findByPrecioLessThan(Double precio);
    public List<productos> findByProveedorIdIsAndStockGreaterThan(Integer proveedorId, Integer stock);


    //queries
    @Query(value = "select * from productos where precio < :precio and active = true;", nativeQuery = true)
    public List<productos> productosBaratos(Double precio);
}