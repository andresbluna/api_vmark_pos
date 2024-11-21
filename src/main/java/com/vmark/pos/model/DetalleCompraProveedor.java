package com.vmark.pos.model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "VMARK_DETALLE_COMPRA_PROVEEDORES")
public class DetalleCompraProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_compra_seq")
    @SequenceGenerator(name = "detalle_compra_seq", sequenceName = "DETALLE_COMPRA_SEQ", allocationSize = 1)
    private Long detalleCompraId;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "PRECIO_UNITARIO", nullable = false)
    private BigDecimal precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPRA_ID", nullable = false)
    private CompraProveedor compra;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private Producto producto;


    public DetalleCompraProveedor() {}

    public DetalleCompraProveedor(Integer cantidad, BigDecimal precioUnitario, CompraProveedor compra, Producto producto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.compra = compra;
        this.producto = producto;
    }

    public Long getDetalleCompraId() {
        return detalleCompraId;
    }

    public void setDetalleCompraId(Long detalleCompraId) {
        this.detalleCompraId = detalleCompraId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public CompraProveedor getCompra() {
        return compra;
    }

    public void setCompra(CompraProveedor compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


}
