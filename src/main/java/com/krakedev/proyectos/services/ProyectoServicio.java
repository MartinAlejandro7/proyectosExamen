package com.krakedev.proyectos.services;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServicio {

    private final ProyectoRepository proyectoRepository;

    public ProyectoServicio(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    public Proyecto buscar(int id) {
        Optional<Proyecto> resultado = proyectoRepository.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("Proyecto con ID " + id + " no encontrado"));
    }

    public Proyecto actualizar(int id, Proyecto datos) {
        Proyecto proyecto = buscar(id);
        
        proyecto.setNombre(datos.getNombre());
        proyecto.setDescripcion(datos.getDescripcion());
        proyecto.setFechaInicio(datos.getFechaInicio());
        
        return proyectoRepository.save(proyecto);
    }

    public void eliminar(int id) {
        buscar(id); 
        proyectoRepository.deleteById(id);
    }
}