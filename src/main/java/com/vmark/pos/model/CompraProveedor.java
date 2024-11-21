package com.vmark.pos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "VMARK_COMPRAS_PROVEEDORES")
public class CompraProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
    @SequenceGenerator(name = "compra_seq", sequenceName = "COMPRA_SEQ", allocationSize = 1)
    @Column(name = "COMPRA_ID")
    private Long compraId;

    @Column(name = "FECHA_COMPRA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;

    @Column(name = "MONTO_TOTAL", nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVEEDOR_ID", nullable = false)
    private Proveedor proveedor;

    public CompraProveedor() {}

    public CompraProveedor(Date fechaCompra, BigDecimal montoTotal, String estado, Proveedor proveedor) {
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.estado = estado;
        this.proveedor = proveedor;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
