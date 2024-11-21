package com.vmark.pos.controller;

import com.vmark.pos.model.CategoriaProducto;
import com.vmark.pos.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categoria-productos")
public class CategoriaProductoController {



    private final CategoriaProductoService service;

    @Autowired
    public CategoriaProductoController(CategoriaProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaProducto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProducto> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoriaProducto create(@RequestBody CategoriaProducto categoriaProducto) {
        return service.save(categoriaProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProducto> update(@PathVariable Long id, @RequestBody CategoriaProducto updated) {
        return service.getById(id)
                .map(existing -> {
                    existing.setProductoId(updated.getProductoId());
                    existing.setCategoriaId(updated.getCategoriaId());
                    return ResponseEntity.ok(service.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
