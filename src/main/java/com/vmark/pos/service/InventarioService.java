package com.vmark.pos.service;

import com.vmark.pos.repository.InventarioRepository;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.vmark.pos.repository.VentaRepository.entityManager;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioService(InventarioRepository repository) {
        this.inventarioRepository = repository;
    }





}
