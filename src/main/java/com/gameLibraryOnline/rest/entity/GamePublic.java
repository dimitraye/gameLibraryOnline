package com.gameLibraryOnline.rest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "game_public")
public class GamePublic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Platform> platforms;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<VideoGameGenre> genres;

    private String picture;
}
