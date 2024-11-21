package com.vmark.pos.controller;

import com.vmark.pos.model.Empleado;
import com.vmark.pos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosLosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        return empleadoService.obtenerEmpleadoPorId(id)
                .map(empleado -> new ResponseEntity<>(empleado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoDetalles);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
