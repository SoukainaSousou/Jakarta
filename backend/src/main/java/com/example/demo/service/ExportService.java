package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportService {

    private final ReservationRepository reservationRepository;

    public ExportService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<String[]> getPersonneLocalDataByDate(LocalDate dateDebut, LocalDate dateFin) {
        // Filtrer les réservations par statut "ACCEPTER" et plage de dates
        List<Reservation> reservations = reservationRepository.findByStatutAndDateDebutBetween(
                Reservation.StatutReservation.ACCEPTER, dateDebut.atStartOfDay(), dateFin.atTime(23, 59)
        );

        return reservations.stream()
                .map(reservation -> new String[]{
                        reservation.getPersonne().getNom() + " " + reservation.getPersonne().getPrenom(),
                        reservation.getLocal().getNom(),
                        reservation.getDateDebut().toString(),
                        reservation.getDateFin().toString(),
                })
                .collect(Collectors.toList());
    }
}
