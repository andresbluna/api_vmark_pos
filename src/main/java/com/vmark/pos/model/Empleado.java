package com.vmark.pos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "VMARK_EMPLEADOS_2")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado2_seq")
    @SequenceGenerator(name = "empleado2_seq", sequenceName = "empleado2_seq", allocationSize = 1)
    @Column(name = "empleado_id_p", nullable = false)
    private Long empleadoId;

    @Column(name = "NOMBRE_EMPLEADO_P", nullable = false)
    private String nombreEmpleado;
    @Column(name = "apellido1_empleado_p", nullable = false)
    private String apellido1Empleado;
    private String apellido2Empleado;
    private String contrasenaEmpleado;
    private Long rolEmpleadoId;
    private Double salarioEmpleado;
    private Date fechaContratacion;


    public Empleado() {
    }

    public Empleado(Long empleadoId, String nombreEmpleado, String apellido1Empleado, String apellido2Empleado,
                    String contrasenaEmpleado, Long rolEmpleadoId, Double salarioEmpleado, Date fechaContratacion) {
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.apellido1Empleado = apellido1Empleado;
        this.apellido2Empleado = apellido2Empleado;
        this.contrasenaEmpleado = contrasenaEmpleado;
        this.rolEmpleadoId = rolEmpleadoId;
        this.salarioEmpleado = salarioEmpleado;
        this.fechaContratacion = fechaContratacion;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellido1Empleado() {
        return apellido1Empleado;
    }

    public void setApellido1Empleado(String apellido1Empleado) {
        this.apellido1Empleado = apellido1Empleado;
    }

    public String getApellido2Empleado() {
        return apellido2Empleado;
    }

    public void setApellido2Empleado(String apellido2Empleado) {
        this.apellido2Empleado = apellido2Empleado;
    }

    public String getContrasenaEmpleado() {
        return contrasenaEmpleado;
    }

    public void setContrasenaEmpleado(String contrasenaEmpleado) {
        this.contrasenaEmpleado = contrasenaEmpleado;
    }

    public Long getRolEmpleadoId() {
        return rolEmpleadoId;
    }

    public void setRolEmpleadoId(Long rolEmpleadoId) {
        this.rolEmpleadoId = rolEmpleadoId;
    }

    public Double getSalarioEmpleado() {
        return salarioEmpleado;
    }

    public void setSalarioEmpleado(Double salarioEmpleado) {
        this.salarioEmpleado = salarioEmpleado;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}

