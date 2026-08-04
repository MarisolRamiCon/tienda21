package com.inndata21.tienda21.dto.APITerceros;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Viajes {
    private Integer id;
    private LocalDateTime fechaSalida;
    private String direccion;
    private Double precio;
}
