package com.vmark.pos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "VMARK_INVENTARIO")
public class Inventario {


    @Id
    @Column(name = "INVENTARIO_ID")
    private Long id;

    @Column(name = "CANTIDAD_ACTUAL", nullable = false)
    private Integer cantidadActual;

    @Column(name = "FECHA_ULTIMA_ACTUALIZACION", nullable = false)
    private LocalDate fechaUltimaActualizacion;

    @Column(name = "PRODUCTO_ID", nullable = false)
    private Long productoId;

    // Constructor vacío
    public Inventario() {
    }

    // Constructor con todos los campos
    public Inventario(Long id, Integer cantidadActual, LocalDate fechaUltimaActualizacion, Long productoId) {
        this.id = id;
        this.cantidadActual = cantidadActual;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.productoId = productoId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public LocalDate getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDate fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}
