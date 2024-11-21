package com.vmark.pos.controller;

import com.vmark.pos.model.Boleta;
import com.vmark.pos.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/boletas")
public class BoletaController {


    private final BoletaService service;

    @Autowired
    public BoletaController(BoletaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Boleta> getAllBoletas() {
        return service.getAllBoletas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> getBoletaById(@PathVariable Long id) {
        return service.getBoletaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Boleta createBoleta(@RequestBody Boleta boleta) {
        return service.saveBoleta(boleta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boleta> updateBoleta(@PathVariable Long id, @RequestBody Boleta updatedBoleta) {
        return service.getBoletaById(id)
                .map(existingBoleta -> {
                    existingBoleta.setCantidad(updatedBoleta.getCantidad());
                    existingBoleta.setPrecioUnitario(updatedBoleta.getPrecioUnitario());
                    existingBoleta.setProductoId(updatedBoleta.getProductoId());
                    existingBoleta.setVentaId(updatedBoleta.getVentaId());
                    return ResponseEntity.ok(service.saveBoleta(existingBoleta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleta(@PathVariable Long id) {
        if (service.getBoletaById(id).isPresent()) {
            service.deleteBoletaById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
