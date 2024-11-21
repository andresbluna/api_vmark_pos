package com.vmark.pos.service;

import com.vmark.pos.model.Boleta;
import com.vmark.pos.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BoletaService {


    private final BoletaRepository repository;

    @Autowired
    public BoletaService(BoletaRepository repository) {
        this.repository = repository;
    }

    public List<Boleta> getAllBoletas() {
        return repository.findAll();
    }

    public Optional<Boleta> getBoletaById(Long id) {
        return repository.findById(id);
    }

    public Boleta saveBoleta(Boleta boleta) {
        return repository.save(boleta);
    }

    public void deleteBoletaById(Long id) {
        repository.deleteById(id);
    }
}
