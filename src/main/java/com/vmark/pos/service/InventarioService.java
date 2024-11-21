package com.vmark.pos.service;

import com.vmark.pos.model.Inventario;
import com.vmark.pos.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {



    private final InventarioRepository repository;

    @Autowired
    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public List<Inventario> getAllInventarios() {
        return repository.findAll();
    }

    public Optional<Inventario> getInventarioById(Long id) {
        return repository.findById(id);
    }

    public Inventario saveInventario(Inventario inventario) {
        return repository.save(inventario);
    }

    public void deleteInventarioById(Long id) {
        repository.deleteById(id);
    }
}
