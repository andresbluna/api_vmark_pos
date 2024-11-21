package com.vmark.pos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "VMARK_CATEGORIA_PRODUCTOS")
public class CategoriaProducto {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORIA_PRODUCTO_ID")
    private Long id;

    @Column(name = "PRODUCTO_ID", nullable = false)
    private Long productoId;

    @Column(name = "CATEGORIA_ID", nullable = false)
    private Long categoriaId;

    // Constructor vacío
    public CategoriaProducto() {
    }

    // Constructor con todos los campos
    public CategoriaProducto(Long id, Long productoId, Long categoriaId) {
        this.id = id;
        this.productoId = productoId;
        this.categoriaId = categoriaId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
