package com.example.demo.controller;



import com.example.demo.model.Personne;
import com.example.demo.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/personnes")
@CrossOrigin(origins = "http://localhost:8081")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneService.getAllPersonnes();
    }

    @GetMapping("/{id}")
    public Personne getPersonneById(@PathVariable Long id) {
        return personneService.getPersonneById(id).orElseThrow(() -> new RuntimeException("Personne non trouvée"));
    }

    @PostMapping
    public Personne createPersonne(@RequestBody Personne personne) {
        return personneService.createPersonne(personne);
    }

    @PutMapping("/{id}")
    public Personne updatePersonne(@PathVariable Long id, @RequestBody Personne updatedPersonne) {
        return personneService.updatePersonne(id, updatedPersonne);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable Long id) {
        personneService.deletePersonne(id);
    }
}
