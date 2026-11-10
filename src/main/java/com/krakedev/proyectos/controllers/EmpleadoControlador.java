package com.krakedev.proyectos.controllers;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.services.EmpleadoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @GetMapping
    public List<Empleado> listar() {
        return empleadoServicio.listar();
    }

    @GetMapping("/{id}")
    public Empleado buscarPorId(@PathVariable int id) {
        return empleadoServicio.buscar(id);
    }

    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado) {
        return empleadoServicio.guardar(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable int id, @RequestBody Empleado empleado) {
        return empleadoServicio.actualizar(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        empleadoServicio.eliminar(id);
    }
}