package com.gameLibraryOnline.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private boolean success;
    private String token; // ← Ajoute ce champ
    private String role;


    public AuthResponse(String message, boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token;
        this.role = null;
    }
}
