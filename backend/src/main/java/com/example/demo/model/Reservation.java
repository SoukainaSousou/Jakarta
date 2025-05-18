/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;



@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @ManyToOne
@JoinColumn(name = "id_local")
private Local local;


    @ManyToOne
    @JoinColumn(name = "idPersonne", nullable = false)
    private Personne personne;  // Relation avec l'entité Personne (qui représente la personne ayant effectué la réservation)

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
private LocalDateTime dateDebut;

@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
private LocalDateTime dateFin;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutReservation statut;  // Statut de la réservation (accepter, annuler, en attente)

    // Getters et Setters
    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    // Enum pour les statuts de réservation
    public enum StatutReservation {
        ACCEPTER,      // Réservation acceptée
        ANNULER,       // Réservation annulée
        EN_ATTENTE     // Réservation en attente
    }
}
