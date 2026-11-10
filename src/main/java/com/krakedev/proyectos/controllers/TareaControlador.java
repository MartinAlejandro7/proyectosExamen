package com.krakedev.proyectos.controllers;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.services.TareaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaControlador {

    private final TareaServicio tareaServicio;

    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    @GetMapping
    public List<Tarea> listar() {
        return tareaServicio.listar();
    }

    @GetMapping("/{id}")
    public Tarea buscarPorId(@PathVariable int id) {
        return tareaServicio.buscar(id);
    }

    @PostMapping
    public Tarea guardar(@RequestBody Tarea tarea) {
        return tareaServicio.guardar(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable int id, @RequestBody Tarea tarea) {
        return tareaServicio.actualizar(id, tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        tareaServicio.eliminar(id);
    }
}