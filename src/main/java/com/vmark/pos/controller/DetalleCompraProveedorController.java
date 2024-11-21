package com.vmark.pos.controller;

import com.vmark.pos.model.DetalleCompraProveedor;
import com.vmark.pos.service.DetalleCompraProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-compras")
public class DetalleCompraProveedorController {

    private final DetalleCompraProveedorService detalleCompraProveedorService;

    @Autowired
    public DetalleCompraProveedorController(DetalleCompraProveedorService detalleCompraProveedorService) {
        this.detalleCompraProveedorService = detalleCompraProveedorService;
    }

    @PostMapping
    public ResponseEntity<DetalleCompraProveedor> crearDetalle(@RequestBody DetalleCompraProveedor detalle) {
        DetalleCompraProveedor nuevoDetalle = detalleCompraProveedorService.crearDetalle(detalle);
        return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DetalleCompraProveedor>> obtenerTodasLosDetalles() {
        List<DetalleCompraProveedor> detalles = detalleCompraProveedorService.obtenerTodosLosDetalles();
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompraProveedor> obtenerDetallePorId(@PathVariable Long id) {
        return detalleCompraProveedorService.obtenerDetallePorId(id)
                .map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompraProveedor> actualizarDetalle(@PathVariable Long id, @RequestBody DetalleCompraProveedor detalles) {
        DetalleCompraProveedor detalleActualizado = detalleCompraProveedorService.actualizarDetalle(id, detalles);
        return new ResponseEntity<>(detalleActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detalleCompraProveedorService.eliminarDetalle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
