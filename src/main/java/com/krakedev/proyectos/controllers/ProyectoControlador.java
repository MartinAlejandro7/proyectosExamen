package com.krakedev.proyectos.controllers;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.services.ProyectoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

    private final ProyectoServicio proyectoService;

    public ProyectoControlador(ProyectoServicio proyectoService) {
        this.proyectoService = proyectoService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto) {
        Proyecto nuevoProyecto = proyectoService.guardar(proyecto);
        return ResponseEntity.ok(nuevoProyecto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Proyecto>> listarProyectos() {
        // Corregido a listar() para que coincida con tu servicio
        List<Proyecto> proyectos = proyectoService.listar();
        return ResponseEntity.ok(proyectos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProyecto(@PathVariable Integer id) {
        proyectoService.eliminar(id);
        return ResponseEntity.ok().body("Proyecto eliminado exitosamente");
    }
}