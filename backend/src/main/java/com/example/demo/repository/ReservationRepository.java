package com.example.demo.repository;

import com.example.demo.model.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
      List<Reservation> findByStatutAndDateDebutBetween(
            Reservation.StatutReservation statut,
            LocalDateTime dateDebut,
            LocalDateTime dateFin
    );
    // Récupérer les réservations par statut
    List<Reservation> findByStatut(Reservation.StatutReservation statut);

    // Rechercher des réservations en conflit pour un créneau donné
    @Query("SELECT r FROM Reservation r WHERE r.local.id = :idLocal " +
           "AND r.statut = 'ACCEPTER' " +
           "AND (r.dateDebut < :dateFin AND r.dateFin > :dateDebut)")
    List<Reservation> findReservationsByLocalAndDate(
            @Param("idLocal") Long idLocal,
            @Param("dateDebut") LocalDateTime dateDebut,
            @Param("dateFin") LocalDateTime dateFin
    );

    // Récupérer les réservations par personne
    List<Reservation> findByPersonneId(Long idPersonne);

    // Récupérer les réservations dans une plage de dates et par statut
    @Query("SELECT r FROM Reservation r WHERE r.statut = :statut " +
           "AND r.dateDebut >= :dateDebut " +
           "AND r.dateFin <= :dateFin")
    List<Reservation> findByStatutAndDateRange(
            @Param("statut") Reservation.StatutReservation statut,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );
    @Query("SELECT r.local.nom AS local, COUNT(r) AS reservations " +
           "FROM Reservation r GROUP BY r.local.nom")
    List<Map<String, Object>> countReservationsByLocal();

    @Query("SELECT r.local.nom AS local, " +
           "SUM(TIMESTAMPDIFF(HOUR, r.dateDebut, r.dateFin)) AS heuresReservees " +
           "FROM Reservation r GROUP BY r.local.nom")
    List<Map<String, Object>> calculateTauxOccupation();

    @Query("SELECT r.personne.nom AS personne, COUNT(r) AS reservations " +
           "FROM Reservation r GROUP BY r.personne.nom")
    List<Map<String, Object>> countReservationsByPersonne();
}
