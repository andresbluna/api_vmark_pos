package com.vmark.pos.controller;


import com.vmark.pos.model.Venta;
import com.vmark.pos.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/promedio-diario")
    public ResponseEntity<Double> obtenerPromedioVentaDiaria() {
        Double promedio = ventaService.obtenerPromedioVentaDiaria();
        return ResponseEntity.ok(promedio);
    }
}
