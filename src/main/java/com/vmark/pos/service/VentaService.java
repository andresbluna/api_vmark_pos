package com.vmark.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class VentaService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VentaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Double obtenerPromedioVentaDiaria() {
        String sql = "SELECT promedio_venta_diaria FROM dual";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }


}
