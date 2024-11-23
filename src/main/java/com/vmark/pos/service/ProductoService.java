package com.vmark.pos.service;

import com.vmark.pos.model.Producto;
import com.vmark.pos.repository.ProductoRepository;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.vmark.pos.repository.VentaRepository.entityManager;


@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Map<String, Object> obtenerProductosStockBajo() {
        Map<String, Object> resultado = new HashMap<>();

        try {
            List<Object[]> productos = productoRepository.findProductosStockBajo();

            if (!productos.isEmpty()) {
                List<Map<String, Object>> productosDetalle = productos.stream()
                        .map(producto -> {
                            Map<String, Object> detalle = new HashMap<>();
                            detalle.put("productoId", producto[0]);
                            detalle.put("nombre", producto[1]);
                            detalle.put("descripcion", producto[2]);
                            detalle.put("precio", producto[3]);
                            detalle.put("stock", producto[4]);
                            detalle.put("categoriaId", producto[5]);
                            return detalle;
                        })
                        .collect(Collectors.toList());

                resultado.put("mensaje", "¡Alerta! Hay " + productos.size() + " productos con stock bajo");
                resultado.put("productos", productosDetalle);
            } else {
                resultado.put("mensaje", "No hay productos con stock bajo");
                resultado.put("productos", Collections.emptyList());
            }

            resultado.put("status", "success");

        } catch (Exception e) {
            resultado.put("status", "error");
            resultado.put("mensaje", "Error al verificar stock: " + e.getMessage());
        }

        return resultado;
    }
}
