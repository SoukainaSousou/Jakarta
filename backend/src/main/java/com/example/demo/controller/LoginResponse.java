/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

public class LoginResponse {
    private boolean success;
    private String message;  
    private Long personneId;
    
 

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
       public Long getPersonneId() {
        return personneId;
    }

    public void setPersonneId(Long personneId) {
        this.personneId = personneId;
    }
}
