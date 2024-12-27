package com.gameLibraryOnline.rest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="progression")
public class Progression {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long idProgression;

    @Column(name = "detailsProgression")
    private String detailsProgression;

    @Column(name = "percentageCompletion")
    private int percentageCompletion;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}
