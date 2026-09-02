package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Conductor;
import com.example.demo.repository.ConductorRepository;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;

    public ConductorService(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public Conductor registrarConductor(Conductor conductor) {
        return conductorRepository.save(conductor);
    }

    public List<Conductor> obtenerTodos() {
        return conductorRepository.findAll();
    }

    public Conductor obtenerPorId(Long id) {
        return conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
    }

    public Conductor actualizarConductor(Long id, Conductor conductor) {

        Conductor conductorExistente = obtenerPorId(id);

        conductorExistente.setNombre(conductor.getNombre());
        conductorExistente.setDocumento(conductor.getDocumento());

        return conductorRepository.save(conductorExistente);
    }

    public void eliminarConductor(Long id) {

        if (!conductorRepository.existsById(id)) {
            throw new RuntimeException("Conductor no encontrado");
        }

        conductorRepository.deleteById(id);
    }
}