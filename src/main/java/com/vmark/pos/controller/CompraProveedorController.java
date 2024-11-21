package com.vmark.pos.controller;

import com.vmark.pos.model.CompraProveedor;
import com.vmark.pos.service.CompraProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraProveedorController {
    private final CompraProveedorService compraProveedorService;

    @Autowired
    public CompraProveedorController(CompraProveedorService compraProveedorService) {
        this.compraProveedorService = compraProveedorService;
    }

    @PostMapping
    public ResponseEntity<CompraProveedor> crearCompra(@RequestBody CompraProveedor compraProveedor) {
        CompraProveedor nuevaCompra = compraProveedorService.crearCompra(compraProveedor);
        return new ResponseEntity<>(nuevaCompra, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompraProveedor>> obtenerTodasLasCompras() {
        List<CompraProveedor> compras = compraProveedorService.obtenerTodasLasCompras();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraProveedor> obtenerCompraPorId(@PathVariable Long id) {
        return compraProveedorService.obtenerCompraPorId(id)
                .map(compra -> new ResponseEntity<>(compra, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraProveedor> actualizarCompra(@PathVariable Long id, @RequestBody CompraProveedor detallesCompra) {
        CompraProveedor compraActualizada = compraProveedorService.actualizarCompra(id, detallesCompra);
        return new ResponseEntity<>(compraActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        compraProveedorService.eliminarCompra(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
