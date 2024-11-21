package com.vmark.pos.controller;

import com.vmark.pos.model.Empleado;
import com.vmark.pos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {/*

    private final EmpleadoService empleadoService;

    @Autowired
    public AuthController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Empleado empleado) {
        // Aquí deberías verificar las credenciales del usuario
        Empleado usuario = empleadoService.obtenerEmpleadoPorEmail(empleado.getEmail());
        if (usuario != null && usuario.getPassword().equals(empleado.getPassword())) {
            // Generar un token JWT o establecer una sesión
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }*/
}
