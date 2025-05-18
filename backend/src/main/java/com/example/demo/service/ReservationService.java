/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

   
    /**
     * Récupérer les réservations par statut
     */
    public List<Reservation> getReservationsByStatut(Reservation.StatutReservation statut) {
        return reservationRepository.findByStatut(statut);
    }

    /**
     * Traiter une réservation (Accepter ou Refuser)
     */
    public Reservation traiterReservation(Long id, Reservation.StatutReservation statut) {
        // Récupérer la réservation
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));

        // Si le statut est ACCEPTÉ, vérifier les conflits
        if (statut == Reservation.StatutReservation.ACCEPTER) {
            boolean conflit = verifierConflit(
                    reservation.getLocal().getIdLocal(),
                    reservation.getDateDebut(),
                    reservation.getDateFin()
            );

            if (conflit) {
                throw new RuntimeException("Conflit détecté : cet amphithéâtre est déjà réservé pour ce créneau.");
            }
        }

        // Mettre à jour le statut
        reservation.setStatut(statut);
        Reservation updatedReservation = reservationRepository.save(reservation);

       
        return updatedReservation;
    }

    /**
     * Vérifier les conflits de réservation
     */
    private boolean verifierConflit(Long idLocal, LocalDateTime dateDebut, LocalDateTime dateFin) {
        List<Reservation> reservations = reservationRepository.findReservationsByLocalAndDate(
                idLocal, dateDebut, dateFin
        );
        return !reservations.isEmpty();
    }
   public List<Reservation> getReservationsByPersonne(Long id) {
    return reservationRepository.findByPersonneId(id);
}

    
}
