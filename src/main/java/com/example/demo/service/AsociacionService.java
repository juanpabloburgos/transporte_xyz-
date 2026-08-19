package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Camion;
import com.example.demo.model.Conductor;
import com.example.demo.repository.CamionRepository;
import com.example.demo.repository.ConductorRepository;

@Service
public class AsociacionService {

    private final CamionRepository camionRepository;
    private final ConductorRepository conductorRepository;

    public AsociacionService(
            CamionRepository camionRepository,
            ConductorRepository conductorRepository) {

        this.camionRepository = camionRepository;
        this.conductorRepository = conductorRepository;
    }

    public void asociarConductor(Long camionId, Long conductorId) {

        Camion camion = camionRepository.findById(camionId)
                .orElseThrow(() -> new RuntimeException("Camión no encontrado"));

        Conductor conductor = conductorRepository.findById(conductorId)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));

        camion.setConductor(conductor);

        camionRepository.save(camion);
    }
}