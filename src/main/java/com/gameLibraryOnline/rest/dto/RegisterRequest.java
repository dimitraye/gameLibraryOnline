package com.gameLibraryOnline.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    public String username;
    public String password;
    public String firstname;
    public String lastname;
    public String email;
    public String birthDate;
    public Boolean gender;
    public String adress;
    public String postCode;
    public String city;
    public String country;
    public String phoneNumber;
}

