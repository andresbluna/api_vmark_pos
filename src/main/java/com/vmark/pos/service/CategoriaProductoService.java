package com.vmark.pos.service;

import com.vmark.pos.model.CategoriaProducto;
import com.vmark.pos.repository.CategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProductoService {



    private final CategoriaProductoRepository repository;

    @Autowired
    public CategoriaProductoService(CategoriaProductoRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaProducto> getAll() {
        return repository.findAll();
    }

    public Optional<CategoriaProducto> getById(Long id) {
        return repository.findById(id);
    }

    public CategoriaProducto save(CategoriaProducto categoriaProducto) {
        return repository.save(categoriaProducto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
