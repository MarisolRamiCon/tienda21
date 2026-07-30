package com.inndata21.tienda21.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//Anotation Lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name= "pedido")

public class pedido {

    @Id
    @Column(name = "id_pedido")
    private Integer idPedido;
    
    @Column(name = "fecha_pedido")
    private LocalDateTime fecha_pedido;

    @Column(name = "cliente_id")
    private Integer cliente_id;

    @Column(name = "total_pedido")
    private Double total_pedido;
}
