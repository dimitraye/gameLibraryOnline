package com.example.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDUSER")
    private long idUser;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "BIRTHDATE")
    private String birthDate;

    @Column(name = "GENDER")
    private Boolean gender;
    @Column(name = "ADRESS")
    private String adress;

    @Column(name = "POSTCODE")
    private String postCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "REGISTRATION_DATE")
    private String registrationDate;

    @Column(name = "ROLE")
    private Role role;
}
