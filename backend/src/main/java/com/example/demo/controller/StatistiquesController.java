/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.service.LocalService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistiques")
@CrossOrigin(origins = "http://localhost:8081")
public class StatistiquesController {

    private final LocalService localService;

    public StatistiquesController(LocalService localService) {
        this.localService = localService;
    }

    // Statistique 1 : Nombre total de réservations par local
    @GetMapping("/reservations-par-local")
    public ResponseEntity<List<Map<String, Object>>> getReservationsParLocal() {
        List<Map<String, Object>> statistiques = localService.getReservationsParLocal();
        return ResponseEntity.ok(statistiques);
    }

    // Statistique 2 : Taux d'occupation des locaux
    @GetMapping("/taux-occupation")
    public ResponseEntity<List<Map<String, Object>>> getTauxOccupation() {
        List<Map<String, Object>> statistiques = localService.getTauxOccupation();
        return ResponseEntity.ok(statistiques);
    }

    // Statistique 3 : Réservations par utilisateur
    @GetMapping("/reservations-par-personne")
    public ResponseEntity<List<Map<String, Object>>> getReservationsParPersonne() {
        List<Map<String, Object>> statistiques = localService.getReservationsParPersonne();
        return ResponseEntity.ok(statistiques);
    }
}
