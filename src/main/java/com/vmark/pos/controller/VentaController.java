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
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.crearVenta(venta);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        return ventaService.obtenerVentaPorId(id)
                .map(venta -> new ResponseEntity<>(venta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody Venta ventaDetalles) {
        Venta ventaActualizada = ventaService.actualizarVenta(id, ventaDetalles);
        return new ResponseEntity<>(ventaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
