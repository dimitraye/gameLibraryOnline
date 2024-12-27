package com.gameLibraryOnline.rest.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "statistic")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nbTotalGames")
    private int nbTotalGames;

    @Column(name = "nbGamesNotStarted")
    private int nbGamesNotStarted;

    @Column(name = "nbGamesFinished")
    private int ngGamesFinished;

    @Column(name = "nbGamesByPlateform")
    private int nbGamesByPlateform;

    @Column(name = "nbGamesByGenre")
    private int nbGamesByGenre;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
