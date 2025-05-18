package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Personne;
import com.example.demo.repository.PersonneRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    private final PersonneRepository personneRepository;

    @Autowired
    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public Personne findByEmail(String email) {
        return personneRepository.findByEmail(email); // Appel à la méthode du repository
    }
   public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    public Optional<Personne> getPersonneById(Long id) {
        return personneRepository.findById(id);
    }

    public Personne createPersonne(Personne personne) {
        return personneRepository.save(personne);
    }
public Personne updatePersonne(Long id, Personne updatedPersonne) {
    return personneRepository.findById(id).map(personne -> {
        // Mise à jour des champs de la personne
        personne.setNom(updatedPersonne.getNom());
        personne.setPrenom(updatedPersonne.getPrenom());
        personne.setDateNaissance(updatedPersonne.getDateNaissance());
        personne.setEmail(updatedPersonne.getEmail());
        personne.setCin(updatedPersonne.getCin());
        personne.setTelephone(updatedPersonne.getTelephone());
        personne.setGrade(updatedPersonne.getGrade());
        personne.setAdresse(updatedPersonne.getAdresse());
        personne.setVille(updatedPersonne.getVille());
        personne.setCodePostal(updatedPersonne.getCodePostal());
        personne.setResponsabilite(updatedPersonne.getResponsabilite());
        personne.setBanqueNom(updatedPersonne.getBanqueNom());
        personne.setSomNumero(updatedPersonne.getSomNumero());
        personne.setMotDePasse(updatedPersonne.getMotDePasse());
        
        // Sauvegarde de la personne mise à jour
        return personneRepository.save(personne);
    }).orElseThrow(() -> new RuntimeException("Personne non trouvée"));
}


    public void deletePersonne(Long id) {
        personneRepository.deleteById(id);
    }
}
