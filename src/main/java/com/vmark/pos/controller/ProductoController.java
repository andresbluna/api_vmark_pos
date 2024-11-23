package com.vmark.pos.controller;

import com.vmark.pos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<Map<String, Object>> obtenerProductosStockBajo() {
        Map<String, Object> resultado = productoService.obtenerProductosStockBajo();

        if ("error".equals(resultado.get("status"))) {
            return ResponseEntity.internalServerError().body(resultado);
        }

        return ResponseEntity.ok(resultado);
    }
}
