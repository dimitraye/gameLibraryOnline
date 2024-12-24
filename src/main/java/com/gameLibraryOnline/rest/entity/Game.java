package com.gameLibraryOnline.rest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idGame;

    @Column(name = "title")
    private String title;

    @Column(name = "plateform")
    private Platform platform;

    @Column(name = "gameGenre")
    private VideoGameGenre videoGameGenre;

    @Column(name = "datePurchase")
    private Date datePurchase;

    @Column(name = "playHours")
    private int playHours;

    @Column(name = "state")
    private GameStatus state;

    @Column(name = "picture")
    private String picture;

}
