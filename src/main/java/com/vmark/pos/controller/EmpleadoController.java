package com.vmark.pos.controller;

import com.vmark.pos.dto.LoginResult;
import com.vmark.pos.service.EmpleadoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {


    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResult result = empleadoService.validarLogin(
                loginRequest.getNombreEmpleado(),
                loginRequest.getContrasena()
        );

        if (result.getIsValid() == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}

@Data
class LoginRequest {
    private String nombreEmpleado;
    private String contrasena;
}
