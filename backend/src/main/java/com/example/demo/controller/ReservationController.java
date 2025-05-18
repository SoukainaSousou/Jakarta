package com.example.demo.controller;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Local;
import com.example.demo.model.Personne;
import com.example.demo.model.Reservation;
import com.example.demo.model.Reservation.StatutReservation;
import com.example.demo.repository.LocalRepository;
import com.example.demo.repository.PersonneRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService; // Ajoutez l'import pour le service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:8081")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ReservationService reservationService;

    // Créer une réservation
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            Local local = localRepository.findById(reservationDTO.getIdLocal())
                    .orElseThrow(() -> new RuntimeException("Local introuvable"));
            Personne personne = personneRepository.findById(reservationDTO.getIdPersonne())
                    .orElseThrow(() -> new RuntimeException("Personne introuvable"));

            Reservation reservation = new Reservation();
            reservation.setLocal(local);
            reservation.setPersonne(personne);
            reservation.setDateDebut(LocalDateTime.parse(reservationDTO.getDateDebut()));
            reservation.setDateFin(LocalDateTime.parse(reservationDTO.getDateFin()));
            reservation.setStatut(Reservation.StatutReservation.valueOf(reservationDTO.getStatus()));

            reservationRepository.save(reservation);
            return ResponseEntity.ok("Réservation créée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création de la réservation : " + e.getMessage());
        }
    }

    // Obtenir toutes les réservations
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }

    // Obtenir une réservation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtenir les réservations en attente
    @GetMapping("/en-attente")
    public ResponseEntity<List<Reservation>> getReservationsEnAttente() {
        try {
            List<Reservation> reservations = reservationService.getReservationsByStatut(StatutReservation.EN_ATTENTE);
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Gestion des erreurs
        }
    }

    // Supprimer une réservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/enseignant/{id}")
public ResponseEntity<List<Reservation>> getReservationsByEnseignant(@PathVariable Long id) {
    System.out.println("ID de l'enseignant reçu: " + id);  // Log pour vérifier l'ID
    try {
        List<Reservation> reservations = reservationService.getReservationsByPersonne(id);
        return ResponseEntity.ok(reservations);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

    // Traiter une réservation
    @PutMapping("/{id}/traiter")
    public ResponseEntity<Reservation> traiterReservation(@PathVariable Long id, @RequestParam Reservation.StatutReservation statut) {
        try {
            if (statut == null) {
                return ResponseEntity.badRequest().body(null);
            }

            // Mettre à jour le statut de la réservation
            Reservation updatedReservation = reservationService.traiterReservation(id, statut);

            return ResponseEntity.ok(updatedReservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
