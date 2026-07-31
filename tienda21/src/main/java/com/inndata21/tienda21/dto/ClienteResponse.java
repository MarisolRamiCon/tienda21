package com.inndata21.tienda21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteResponse {
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;
}
