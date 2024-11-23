package com.vmark.pos.dto;

import lombok.Data;
import java.util.List;

@Data
public class VentaRequestDTO {

    private Long empleadoId;
    private String metodoPago;
    private List<Integer> cantidades;    // Cambiado a Integer
    private List<Integer> precios;        // Cambiado a Double
    private List<Long> productosId;
}
