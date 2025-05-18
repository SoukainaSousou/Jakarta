package com.example.demo.service;

import com.example.demo.model.Local;
import com.example.demo.repository.LocalRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;
    private final ReservationRepository reservationRepository;

    public List<Local> getAllLocaux() {
        return localRepository.findAll();
    }

    public Optional<Local> getLocalById(Long id) {
        return localRepository.findById(id);
    }

    public Local saveLocal(Local local) {
        return localRepository.save(local);
    }

    public Optional<Local> updateLocal(Long id, Local local) {
        if (localRepository.existsById(id)) {
            local.setIdLocal(id);
            return Optional.of(localRepository.save(local));
        }
        return Optional.empty();
    }

    public boolean deleteLocal(Long id) {
        if (localRepository.existsById(id)) {
            localRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public LocalService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // Calcul du nombre de réservations par local
    public List<Map<String, Object>> getReservationsParLocal() {
        return reservationRepository.countReservationsByLocal();
    }

    // Calcul du taux d'occupation par local
    public List<Map<String, Object>> getTauxOccupation() {
        return reservationRepository.calculateTauxOccupation();
    }

    // Calcul des réservations par personne
    public List<Map<String, Object>> getReservationsParPersonne() {
        return reservationRepository.countReservationsByPersonne();
    }
}
