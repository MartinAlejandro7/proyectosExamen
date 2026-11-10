package com.krakedev.proyectos.controllers;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.services.ProyectoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

    private final ProyectoServicio proyectoServicio;

    public ProyectoControlador(ProyectoServicio proyectoServicio) {
        this.proyectoServicio = proyectoServicio;
    }

    @GetMapping
    public List<Proyecto> listar() {
        return proyectoServicio.listar();
    }

    @GetMapping("/{id}")
    public Proyecto buscarPorId(@PathVariable int id) {
        return proyectoServicio.buscar(id);
    }

    @PostMapping
    public Proyecto guardar(@RequestBody Proyecto proyecto) {
        return proyectoServicio.guardar(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable int id, @RequestBody Proyecto proyecto) {
        return proyectoServicio.actualizar(id, proyecto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        proyectoServicio.eliminar(id);
    }
}