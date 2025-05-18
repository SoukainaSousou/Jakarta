/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/reservations")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Récupérer les réservations en attente
     */
    @GetMapping("/en-attente")
    public List<Reservation> getReservationsEnAttente() {
        return reservationService.getReservationsByStatut(Reservation.StatutReservation.EN_ATTENTE);
    }

    /**
     * Traiter une réservation (Accepter ou Refuser)
     */
    @PutMapping("/{id}/traiter")
    public ResponseEntity<?> traiterReservation(
            @PathVariable Long id,
            @RequestParam("statut") Reservation.StatutReservation statut) {
        try {
            Reservation reservation = reservationService.traiterReservation(id, statut);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
