/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

public class LoginRequest {

    private String email;
    private String password;

    // Constructeur sans argument
    public LoginRequest() {}

    // Constructeur avec paramètres
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters et setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
