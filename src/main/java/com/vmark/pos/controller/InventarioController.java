package com.vmark.pos.controller;

import com.vmark.pos.model.Inventario;
import com.vmark.pos.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {



    private final InventarioService service;

    @Autowired
    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inventario> getAllInventarios() {
        return service.getAllInventarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Long id) {
        return service.getInventarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inventario createInventario(@RequestBody Inventario inventario) {
        return service.saveInventario(inventario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable Long id, @RequestBody Inventario updatedInventario) {
        return service.getInventarioById(id)
                .map(existingInventario -> {
                    existingInventario.setCantidadActual(updatedInventario.getCantidadActual());
                    existingInventario.setFechaUltimaActualizacion(updatedInventario.getFechaUltimaActualizacion());
                    existingInventario.setProductoId(updatedInventario.getProductoId());
                    return ResponseEntity.ok(service.saveInventario(existingInventario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        if (service.getInventarioById(id).isPresent()) {
            service.deleteInventarioById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
