package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Camion;

public interface CamionRepository extends JpaRepository<Camion, Long> {
}