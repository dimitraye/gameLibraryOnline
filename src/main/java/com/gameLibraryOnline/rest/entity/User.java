package com.gameLibraryOnline.rest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 250)
    private long idUser;

    @Column(name = "username", length = 250)
    private String username;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "firstname", length = 250)
    private String firstname;

    @Column(name = "lastname", length = 250)
    private String lastname;

    @Column(name = "birthDate", length = 250)
    private String birthDate;

    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "asdress", length = 250)
    private String adress;

    @Column(name = "postCode")
    private String postCode;

    @Column(name = "city", length = 250)
    private String city;

    @Column(name = "country", length = 250)
    private String country;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "registrationDate")
    private String registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Game> games;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Progression> progressions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Commentary> commentaries;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Statistic statistic;

}
