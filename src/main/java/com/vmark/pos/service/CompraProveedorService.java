package com.vmark.pos.service;

import com.vmark.pos.model.CompraProveedor;
import com.vmark.pos.repository.CompraProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraProveedorService {

    private final CompraProveedorRepository compraProveedorRepository;

    @Autowired
    public CompraProveedorService(CompraProveedorRepository compraProveedorRepository) {
        this.compraProveedorRepository = compraProveedorRepository;
    }

    public CompraProveedor crearCompra(CompraProveedor compraProveedor) {
        return compraProveedorRepository.save(compraProveedor);
    }

    public List<CompraProveedor> obtenerTodasLasCompras() {
        return compraProveedorRepository.findAll();
    }

    public Optional<CompraProveedor> obtenerCompraPorId(Long id) {
        return compraProveedorRepository.findById(id);
    }

    public CompraProveedor actualizarCompra(Long id, CompraProveedor detallesCompra) {
        return compraProveedorRepository.findById(id).map(compra -> {
            compra.setFechaCompra(detallesCompra.getFechaCompra());
            compra.setMontoTotal(detallesCompra.getMontoTotal());
            compra.setEstado(detallesCompra.getEstado());
            compra.setProveedor(detallesCompra.getProveedor());
            return compraProveedorRepository.save(compra);
        }).orElseThrow(() -> new IllegalArgumentException("Compra no encontrada"));
    }

    public void eliminarCompra(Long id) {
        compraProveedorRepository.deleteById(id);
    }


}
