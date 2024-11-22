package com.vmark.pos.controller;


import com.vmark.pos.model.Venta;
import com.vmark.pos.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/crear")
    public ResponseEntity<BigDecimal> crearVenta(@RequestBody Map<String, Object> requestBody) {
        try {
            // Extraer los datos del cuerpo de la solicitud
            Date fechaVenta = new Date((Long) requestBody.get("fechaVenta"));
            BigDecimal montoTotal = new BigDecimal(requestBody.get("montoTotal").toString());
            String metodoPago = requestBody.get("metodoPago").toString();
            Long empleadoId = Long.parseLong(requestBody.get("empleadoId").toString());

            // Llamar al servicio para crear la venta
            BigDecimal montoRegistrado = ventaService.crearVenta(fechaVenta, montoTotal, metodoPago, empleadoId);

            // Retornar una respuesta exitosa
            return ResponseEntity.ok(montoRegistrado);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/promedio-semanal")
    public ResponseEntity<BigDecimal> obtenerPromedioSemanal() {
        try {
            BigDecimal promedio = ventaService.calcularPromedioSemanal();
            return ResponseEntity.ok(promedio);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


}
