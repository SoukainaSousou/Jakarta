package com.example.demo.controller;

import com.example.demo.model.Local;
import com.example.demo.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locaux")
@CrossOrigin(origins = "http://localhost:8081")
public class LocalController {

    @Autowired
    private LocalService localService;

    @GetMapping
    public List<Local> getAllLocaux() {
        return localService.getAllLocaux();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable Long id) {
        return localService.getLocalById(id)
                .map(local -> ResponseEntity.ok().body(local))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Local> createLocal(@RequestBody Local local) {
        Local createdLocal = localService.saveLocal(local);
        return ResponseEntity.ok(createdLocal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> updateLocal(@PathVariable Long id, @RequestBody Local local) {
        return localService.updateLocal(id, local)
                .map(updatedLocal -> ResponseEntity.ok().body(updatedLocal))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable Long id) {
        if (localService.deleteLocal(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
