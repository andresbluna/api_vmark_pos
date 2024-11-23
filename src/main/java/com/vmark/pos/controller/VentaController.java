package com.vmark.pos.controller;

import com.vmark.pos.dto.VentaRequestDTO;
import com.vmark.pos.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/crear-venta")
    public ResponseEntity<?> crearVenta(@RequestBody VentaRequestDTO request) {
        try {
            String resultado = ventaService.crearVenta(request);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("mensaje", resultado);
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/total-por-rango")
    public ResponseEntity<BigDecimal> obtenerTotalPorRango(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        BigDecimal total = ventaService.calcularTotalPorRango(fechaInicio, fechaFin);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/promedio-por-rango")
    public ResponseEntity<BigDecimal> obtenerPromedioPorRango(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        BigDecimal promedio = ventaService.calcularPromedioPorRango(fechaInicio, fechaFin);
        return ResponseEntity.ok(promedio);
    }

    @GetMapping("/contar-hoy")
    public ResponseEntity<?> contarVentasHoy() {
        return ventaService.obtenerVentasHoy();
    }


}
