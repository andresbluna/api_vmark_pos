package com.vmark.pos.controller;

import com.vmark.pos.model.RolEmpleado;
import com.vmark.pos.service.RolEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/roles")
public class RolEmpleadoController {


    private final RolEmpleadoService service;

    @Autowired
    public RolEmpleadoController(RolEmpleadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<RolEmpleado> getAllRoles() {
        return service.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolEmpleado> getRoleById(@PathVariable Long id) {
        return service.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RolEmpleado createRole(@RequestBody RolEmpleado rolEmpleado) {
        return service.saveRole(rolEmpleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolEmpleado> updateRole(@PathVariable Long id, @RequestBody RolEmpleado updatedRole) {
        return service.getRoleById(id)
                .map(existingRole -> {
                    existingRole.setRol(updatedRole.getRol());
                    return ResponseEntity.ok(service.saveRole(existingRole));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (service.getRoleById(id).isPresent()) {
            service.deleteRoleById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
