package com.krakedev.proyectos.controllers;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.services.EmpleadoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

 
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = empleadoServicio.listar();
        return ResponseEntity.ok(empleados);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable int id) {
        Empleado empleado = empleadoServicio.buscar(id);
        return ResponseEntity.ok(empleado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoServicio.guardar(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable int id, @RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoServicio.actualizar(id, empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        empleadoServicio.eliminar(id);
        return ResponseEntity.ok().body("Empleado eliminado exitosamente");
    }
}