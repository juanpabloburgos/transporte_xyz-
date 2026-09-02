package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Camion;
import com.example.demo.service.CamionService;

@RestController
@RequestMapping("/api/camiones")
public class CamionController {

    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    @PostMapping
    public ResponseEntity<Camion> registrarCamion(@RequestBody Camion camion) {
        return ResponseEntity.ok(camionService.registrarCamion(camion));
    }

    @GetMapping
    public ResponseEntity<List<Camion>> obtenerTodos() {
        return ResponseEntity.ok(camionService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(camionService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Camion> actualizarCamion(
            @PathVariable Long id,
            @RequestBody Camion camion) {

        return ResponseEntity.ok(
                camionService.actualizarCamion(id, camion)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCamion(@PathVariable Long id) {
        camionService.eliminarCamion(id);
        return ResponseEntity.noContent().build();
    }
}