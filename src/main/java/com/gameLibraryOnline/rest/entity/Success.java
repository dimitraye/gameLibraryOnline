package com.gameLibraryOnline.rest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="success")
public class Success {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long idSuccess;

    @Column(name = "description")
    private String description;

    @Column(name = "owned")
    private Boolean owned;

    @ManyToOne
    @JsonBackReference
    private UserGame userGame;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

}
