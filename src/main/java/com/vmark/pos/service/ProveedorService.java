package com.vmark.pos.service;

import com.vmark.pos.model.Proveedor;
import com.vmark.pos.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor actualizarProveedor(Long id, Proveedor detallesProveedor) {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setNombreEmpresa(detallesProveedor.getNombreEmpresa());
            proveedor.setTelefonoEmpresa(detallesProveedor.getTelefonoEmpresa());
            proveedor.setNombreVendedor(detallesProveedor.getNombreVendedor());
            proveedor.setTelefonoVendedor(detallesProveedor.getTelefonoVendedor());
            proveedor.setEmailEmpresa(detallesProveedor.getEmailEmpresa());
            proveedor.setDireccion(detallesProveedor.getDireccion());
            return proveedorRepository.save(proveedor);
        }).orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
    }

    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }


}
