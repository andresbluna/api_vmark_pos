package com.vmark.pos.controller;


import com.vmark.pos.model.Proveedor;
import com.vmark.pos.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.crearProveedor(proveedor);
        return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Long id) {
        return proveedorService.obtenerProveedorPorId(id)
                .map(proveedor -> new ResponseEntity<>(proveedor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor detallesProveedor) {
        Proveedor proveedorActualizado = proveedorService.actualizarProveedor(id, detallesProveedor);
        return new ResponseEntity<>(proveedorActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
