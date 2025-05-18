package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocal;

    @NotNull
    @Size(min = 1, message = "Le nom du local ne peut pas être vide.")
    private String nom;

    @Enumerated(EnumType.STRING)
    private TypeLocal type;

    @Min(value = 1, message = "La capacité doit être un nombre positif.")
    private int capacite;

    private boolean disponibilite = true;

   @ElementCollection
   private List<String> equipements = new ArrayList<>(); // Initialisation par défaut pour éviter un null

    // Getters et Setters
    public Long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeLocal getType() {
        return type;
    }

    public void setType(TypeLocal type) {
        this.type = type;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public List<String> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<String> equipements) {
        this.equipements = equipements;
    }

    // Enum pour le type de local
    public enum TypeLocal {
        AMPHI, SALLE, AUTRE
    }
}
