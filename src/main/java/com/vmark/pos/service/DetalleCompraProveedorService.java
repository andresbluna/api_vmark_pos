package com.vmark.pos.service;

import com.vmark.pos.model.DetalleCompraProveedor;
import com.vmark.pos.model.Producto;
import com.vmark.pos.repository.DetalleCompraProveedorRepository;
import com.vmark.pos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraProveedorService {

    private final DetalleCompraProveedorRepository detalleCompraProveedorRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public DetalleCompraProveedorService(DetalleCompraProveedorRepository detalleCompraProveedorRepository,
                                         ProductoRepository productoRepository) {
        this.detalleCompraProveedorRepository = detalleCompraProveedorRepository;
        this.productoRepository = productoRepository;
    }

    public DetalleCompraProveedor crearDetalle(DetalleCompraProveedor detalle) {
        Producto productoExistente = productoRepository.findById(detalle.getProducto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        if (productoExistente.getNombre() == null || productoExistente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }


        detalle.setProducto(productoExistente);

        return detalleCompraProveedorRepository.save(detalle);
    }

    public List<DetalleCompraProveedor> obtenerTodosLosDetalles() {
        return detalleCompraProveedorRepository.findAll();
    }

    public Optional<DetalleCompraProveedor> obtenerDetallePorId(Long id) {
        return detalleCompraProveedorRepository.findById(id);
    }

    public DetalleCompraProveedor actualizarDetalle(Long id, DetalleCompraProveedor detalles) {
        return detalleCompraProveedorRepository.findById(id).map(detalle -> {
            detalle.setCantidad(detalles.getCantidad());
            detalle.setPrecioUnitario(detalles.getPrecioUnitario());
            detalle.setCompra(detalles.getCompra());
            detalle.setProducto(detalles.getProducto());
            return detalleCompraProveedorRepository.save(detalle);
        }).orElseThrow(() -> new IllegalArgumentException("Detalle no encontrado"));
    }

    public void eliminarDetalle(Long id) {
        detalleCompraProveedorRepository.deleteById(id);
    }

}
