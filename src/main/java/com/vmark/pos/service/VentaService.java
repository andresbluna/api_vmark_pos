package com.vmark.pos.service;

import com.vmark.pos.model.Venta;
import com.vmark.pos.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta actualizarVenta(Long id, Venta ventaDetalles) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));

        venta.setFechaVenta(ventaDetalles.getFechaVenta());
        venta.setMontoTotal(ventaDetalles.getMontoTotal());
        venta.setMetodoPago(ventaDetalles.getMetodoPago());
        venta.setEmpleado(ventaDetalles.getEmpleado());

        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
