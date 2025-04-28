package com.appapi.adapters.input.controller.dto;

public class CreateUserDTO {

    private String username;
    private String password;
    private String email;

    // Construtor vazio
    public CreateUserDTO() {}

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
