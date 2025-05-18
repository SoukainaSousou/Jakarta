package com.example.demo.controller;

import com.example.demo.service.ExportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:8081")
public class ExportController {

    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/export/csv")
    public ResponseEntity<?> exportToCSV(
            @RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {

        // Récupérer les données filtrées par dates
        List<String[]> data = exportService.getPersonneLocalDataByDate(dateDebut, dateFin);

        if (data.isEmpty()) {
            return ResponseEntity.status(404).body("Aucune donnée à exporter pour cette plage de dates.");
        }

        // Construction du fichier CSV
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("Personne,Local,Date de début,Date de fin\n"); // En-tête CSV
        data.forEach(row -> csvBuilder.append(String.join(",", row)).append("\n"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rapport_semaine.csv")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                .body(csvBuilder.toString());
    }
}
