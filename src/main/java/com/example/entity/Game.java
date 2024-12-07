package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="GAME")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDGAME")
    private long idGame;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PLATEFORM")
    private Platform platform;

    @Column(name = "GAMEGENRE")
    private VideoGameGenre videoGameGenre;

    @Column(name = "DATEPURCHASE")
    private Date datePurchase;

    @Column(name = "PLAYHOURS")
    private int playHours;

    @Column(name = "STATE")
    private GameStatus state;

    @Column(name = "PICTURE")
    private String picture;


}
