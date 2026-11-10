package com.krakedev.proyectos.services;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServicio(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    public Empleado buscar(int id) {
        Optional<Empleado> resultado = empleadoRepository.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("Empleado con ID " + id + " no encontrado"));
    }

    public Empleado actualizar(int id, Empleado datos) {
        Empleado empleado = buscar(id);
        
        empleado.setNombre(datos.getNombre());
        empleado.setCargo(datos.getCargo());
        
        return empleadoRepository.save(empleado);
    }

    public void eliminar(int id) {
        buscar(id); 
        empleadoRepository.deleteById(id);
    }
}