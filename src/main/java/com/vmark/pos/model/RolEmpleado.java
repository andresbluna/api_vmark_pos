package com.vmark.pos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VMARK_ROLES")
public class RolEmpleado {



    @Id
    @Column(name = "ROL_EMPLEADO_ID")
    private Long id;

    @Column(name = "ROL_EMPLEADO_ROL", nullable = false, length = 100)
    private String rol;

    // Constructor vacío
    public RolEmpleado() {
    }

    // Constructor con todos los campos
    public RolEmpleado(Long id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
