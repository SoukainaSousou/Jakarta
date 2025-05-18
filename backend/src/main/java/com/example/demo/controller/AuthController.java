package com.example.demo.controller;

import com.example.demo.model.Personne;
import com.example.demo.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    private final PersonneService personneService;
    // Utilisation du BCryptPasswordEncoder

    @Autowired
    public AuthController(PersonneService personneService, AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
        this.personneService = personneService;
    }

  @PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    String email = loginRequest.getEmail();
    String rawPassword = loginRequest.getPassword();  // Le mot de passe en texte clair

    Personne personne = personneService.findByEmail(email);
    if (personne != null && rawPassword.equals(personne.getMotDePasse())) {
        // Vérification de la responsabilité
        if (personne.getResponsabilite() == Personne.Responsabilite.ADMINISTRATEUR) {
            // Connexion réussie pour administrateur
            return ResponseEntity.ok(new LoginResponse(true, "Connexion réussie"));
        } else {
            // Si la responsabilité n'est pas administrateur, on rejette la connexion
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès interdit : utilisateur non autorisé");
        }
    } else {
        // Mot de passe incorrect ou utilisateur non trouvé
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
    }
}
// Méthode pour login pour la réservation d'amphis
    @PostMapping("/login-amphi")
    public ResponseEntity<?> loginAmphi(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String rawPassword = loginRequest.getPassword();  // Le mot de passe en texte clair

        Personne personne = personneService.findByEmail(email);
        if (personne != null && rawPassword.equals(personne.getMotDePasse())) {
            // Pas de vérification de responsabilité ici, tout le monde peut réserver un amphi
            LoginResponse response = new LoginResponse(true, "Connexion réussie pour la réservation d'amphis");
            response.setPersonneId(personne.getId()); // Ajouter l'ID de la personne
            return ResponseEntity.ok(response);
        } else {
            // Mot de passe incorrect ou utilisateur non trouvé
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }




}
