package com.example.demo.controller;

import com.example.demo.service.ExportService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
@Controller
@CrossOrigin(origins = "http://localhost:8081")

public class PdfExportController {

    private final ExportService exportService;

    public PdfExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportToPDF(
            @RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin
    ) {
        // Récupérer les données filtrées par date
        List<String[]> data = exportService.getPersonneLocalDataByDate(dateDebut, dateFin);

        if (data.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Aucune donnée trouvée pour la plage de dates spécifiée.".getBytes());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // Initialiser les objets nécessaires pour le PDF
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Ajouter un titre au PDF
            document.add(new Paragraph("Rapport des Réservations").setBold().setFontSize(16));
            document.add(new Paragraph("Période : " + dateDebut + " - " + dateFin).setFontSize(12).setMarginBottom(10));

            // Créer un tableau pour afficher les données
            Table table = new Table(new float[]{4, 4, 4, 4});
            table.addCell("Personne");
            table.addCell("Local");
            table.addCell("Date de début");
            table.addCell("Date de fin");

            // Ajouter les données au tableau
            for (String[] row : data) {
                for (String cell : row) {
                    table.addCell(cell);
                }
            }

            // Ajouter le tableau au document PDF
            document.add(table);
            document.close(); // Fermer le document pour finaliser la génération
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(("Erreur lors de la génération du PDF : " + e.getMessage()).getBytes());
        }

        // Retourner le fichier PDF généré au client
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rapport_reservations.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(outputStream.toByteArray());
    }
}
