package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "personnes")
public class Personne {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cin;

    private String telephone;
    private String grade;
    private String adresse;
    private String ville;
    private String codePostal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Responsabilite responsabilite;

    @Column(name = "banque_nom")
    private String banqueNom;

    @Column(name = "som_numero", unique = true)
    private String somNumero;

    @Column(nullable = false)
    private String motDePasse;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Responsabilite getResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(Responsabilite responsabilite) {
        this.responsabilite = responsabilite;
    }

    public String getBanqueNom() {
        return banqueNom;
    }

    public void setBanqueNom(String banqueNom) {
        this.banqueNom = banqueNom;
    }

    public String getSomNumero() {
        return somNumero;
    }

    public void setSomNumero(String somNumero) {
        this.somNumero = somNumero;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    // Enum pour la responsabilité
    public enum Responsabilite {
        ADMINISTRATEUR,
        CHEF_DEPARTEMENT,
        ADJOINT_CHEF_DEPARTEMENT,
        DIRECTEUR_LABORATOIRE,
        UTILISATEUR
    }
    
   
}
