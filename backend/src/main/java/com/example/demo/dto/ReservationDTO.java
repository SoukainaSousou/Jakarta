package com.example.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservationDTO {
     
   
   private Long idLocal;
    private Long idPersonne;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String dateFin;
    private String status;


    // Getters et Setters
    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatuts(String status) {
        this.status = status;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }
}
