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

import com.example.demo.model.Conductor;
import com.example.demo.service.ConductorService;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {

    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @PostMapping
    public ResponseEntity<Conductor> registrarConductor(
            @RequestBody Conductor conductor) {

        return ResponseEntity.ok(
                conductorService.registrarConductor(conductor)
        );
    }

    @GetMapping
    public ResponseEntity<List<Conductor>> obtenerTodos() {
        return ResponseEntity.ok(conductorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conductor> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                conductorService.obtenerPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conductor> actualizarConductor(
            @PathVariable Long id,
            @RequestBody Conductor conductor) {

        return ResponseEntity.ok(
                conductorService.actualizarConductor(id, conductor)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConductor(
            @PathVariable Long id) {

        conductorService.eliminarConductor(id);

        return ResponseEntity.noContent().build();
    }
}