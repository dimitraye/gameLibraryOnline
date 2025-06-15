package com.gameLibraryOnline.rest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user_game")
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private GamePublic gamePublic;

    private Date datePurchase;
    private int playHours;
    private GameStatus state;

    @OneToMany(mappedBy = "userGame")
    private List<Progression> progressions;

    @OneToMany(mappedBy = "userGame")
    private List<Success> successes;

    @OneToMany(mappedBy = "userGame")
    private List<Commentary> commentaries;
}
