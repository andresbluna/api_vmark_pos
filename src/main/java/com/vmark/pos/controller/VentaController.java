package com.vmark.pos.controller;

import com.vmark.pos.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<BigDecimal> crearVenta(@RequestBody Map<String, Object> requestBody) {
        try {
            Date fechaVenta = new Date((Long) requestBody.get("fechaVenta"));
            BigDecimal montoTotal = new BigDecimal(requestBody.get("montoTotal").toString());
            String metodoPago = requestBody.get("metodoPago").toString();
            Long empleadoId = Long.parseLong(requestBody.get("empleadoId").toString());

            BigDecimal montoRegistrado = ventaService.crearVenta(fechaVenta, montoTotal, metodoPago, empleadoId);

            return ResponseEntity.ok(montoRegistrado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
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
