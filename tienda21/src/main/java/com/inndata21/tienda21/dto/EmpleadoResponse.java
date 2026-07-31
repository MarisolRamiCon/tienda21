package com.inndata21.tienda21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpleadoResponse {
    private String nombre;
    private String apellido;
    private String puesto;
    private String salario;
    private LocalDate fechaContratacion;
}
