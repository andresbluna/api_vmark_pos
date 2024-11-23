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

    public Map<String, Object> verificarStockBajo() {
        Map<String, Object> resultado = new HashMap<>();

        try {
            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("sp_obtener_stock_bajo")
                    .registerStoredProcedureParameter(
                            1,
                            Class.class,
                            ParameterMode.REF_CURSOR
                    );

            query.execute();
            List<Object[]> productos = query.getResultList();

            if (!productos.isEmpty()) {
                List<Map<String, Object>> productosDetalle = productos.stream()
                        .map(producto -> {
                            Map<String, Object> detalle = new HashMap<>();
                            detalle.put("nombre", producto[0]);
                            detalle.put("stock", producto[1]);
                            return detalle;
                        })
                        .collect(Collectors.toList());

                resultado.put("mensaje", "¡Alerta! Hay productos con stock bajo");
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
