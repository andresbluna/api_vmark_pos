package com.vmark.pos.service;

import com.vmark.pos.model.RolEmpleado;
import com.vmark.pos.repository.RolEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolEmpleadoService {


    private final RolEmpleadoRepository repository;

    @Autowired
    public RolEmpleadoService(RolEmpleadoRepository repository) {
        this.repository = repository;
    }

    public List<RolEmpleado> getAllRoles() {
        return repository.findAll();
    }

    public Optional<RolEmpleado> getRoleById(Long id) {
        return repository.findById(id);
    }

    public RolEmpleado saveRole(RolEmpleado rolEmpleado) {
        return repository.save(rolEmpleado);
    }

    public void deleteRoleById(Long id) {
        repository.deleteById(id);
    }
}
