package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Camion;
import com.example.demo.repository.CamionRepository;

@Service
public class CamionService {

    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    public Camion registrarCamion(Camion camion) {
        return camionRepository.save(camion);
    }

    public List<Camion> obtenerTodos() {
        return camionRepository.findAll();
    }

    public Camion obtenerPorId(Long id) {
        return camionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camión no encontrado"));
    }

    public Camion actualizarCamion(Long id, Camion camion) {

        Camion camionExistente = obtenerPorId(id);

        camionExistente.setPlaca(camion.getPlaca());
        camionExistente.setTipoVehiculo(camion.getTipoVehiculo());

        return camionRepository.save(camionExistente);
    }

    public void eliminarCamion(Long id) {

        if (!camionRepository.existsById(id)) {
            throw new RuntimeException("Camión no encontrado");
        }

        camionRepository.deleteById(id);
    }
}