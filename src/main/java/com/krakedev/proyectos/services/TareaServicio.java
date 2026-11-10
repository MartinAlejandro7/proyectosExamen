package com.krakedev.proyectos.services;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.EmpleadoRepository;
import com.krakedev.proyectos.repositories.ProyectoRepository;
import com.krakedev.proyectos.repositories.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TareaServicio {

    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;
    private final EmpleadoRepository empleadoRepository;

    public TareaServicio(TareaRepository tareaRepository, ProyectoRepository proyectoRepository, EmpleadoRepository empleadoRepository) {
        this.tareaRepository = tareaRepository;
        this.proyectoRepository = proyectoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Tarea guardar(Tarea tarea) {
        
        Proyecto proyecto = proyectoRepository.findById(tarea.getProyecto().getId())
                .orElseThrow(() -> new RuntimeException("Proyecto no existe"));

        List<Empleado> empleadosDB = new ArrayList<>();
        if (tarea.getEmpleados() != null) {
            for (Empleado empleado : tarea.getEmpleados()) {
                Empleado empleadoReal = empleadoRepository.findById(empleado.getId())
                        .orElseThrow(() -> new RuntimeException("Empleado con ID " + empleado.getId() + " no existe"));
                empleadosDB.add(empleadoReal);
            }
        }

        tarea.setProyecto(proyecto);
        tarea.setEmpleados(empleadosDB);

        return tareaRepository.save(tarea);
    }

    public List<Tarea> listar() {
        return tareaRepository.findAll();
    }

    public Tarea buscar(int id) {
        Optional<Tarea> resultado = tareaRepository.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("Tarea con ID " + id + " no encontrada"));
    }

    public Tarea actualizar(int id, Tarea datos) {
        Tarea tarea = buscar(id);
        Proyecto proyecto = proyectoRepository.findById(datos.getProyecto().getId())
                .orElseThrow(() -> new RuntimeException("Proyecto no existe"));

        List<Empleado> empleadosDB = new ArrayList<>();
        if (datos.getEmpleados() != null) {
            for (Empleado empleado : datos.getEmpleados()) {
                Empleado empleadoReal = empleadoRepository.findById(empleado.getId())
                        .orElseThrow(() -> new RuntimeException("Empleado con ID " + empleado.getId() + " no existe"));
                empleadosDB.add(empleadoReal);
            }
        }

        tarea.setDescripcion(datos.getDescripcion());
        tarea.setFechaLimite(datos.getFechaLimite());
        tarea.setCostoEstimado(datos.getCostoEstimado());
        tarea.setProyecto(proyecto);
        tarea.setEmpleados(empleadosDB);

        return tareaRepository.save(tarea);
    }

    public void eliminar(int id) {
        buscar(id);
        tareaRepository.deleteById(id);
    }
}