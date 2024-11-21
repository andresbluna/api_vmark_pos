package com.vmark.pos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "VMARK_PROVEEDORES")

public class Proveedor {

    @Id
    @Column(name = "PROVEEDOR_ID")
    private Long proveedorId;

    @Column(name = "NOMBRE_EMPRESA", nullable = false)
    private String nombreEmpresa;

    @Column(name = "TELEFONO_EMPRESA")
    private String telefonoEmpresa;

    @Column(name = "NOMBRE_VENDEDOR")
    private String nombreVendedor;

    @Column(name = "TELEFONO_VENDEDOR")
    private String telefonoVendedor;

    @Column(name = "EMAIL_EMPRESA")
    private String emailEmpresa;

    @Column(name = "DIRECCION")
    private String direccion;

    // Constructor sin parámetros
    public Proveedor() {}

    // Constructor con todos los parámetros
    public Proveedor(Long proveedorId, String nombreEmpresa, String telefonoEmpresa, String nombreVendedor,
                     String telefonoVendedor, String emailEmpresa, String direccion) {
        this.proveedorId = proveedorId;
        this.nombreEmpresa = nombreEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.nombreVendedor = nombreVendedor;
        this.telefonoVendedor = telefonoVendedor;
        this.emailEmpresa = emailEmpresa;
        this.direccion = direccion;
    }

    // Getters y Setters
    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getTelefonoVendedor() {
        return telefonoVendedor;
    }

    public void setTelefonoVendedor(String telefonoVendedor) {
        this.telefonoVendedor = telefonoVendedor;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



}
