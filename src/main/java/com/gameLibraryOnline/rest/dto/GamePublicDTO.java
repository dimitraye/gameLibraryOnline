package com.gameLibraryOnline.rest.dto;

import com.gameLibraryOnline.rest.entity.Platform;
import com.gameLibraryOnline.rest.entity.VideoGameGenre;
import lombok.Data;

import java.util.List;

@Data
public class GamePublicDTO {
    private Long id;
    private String title;
    private List<Platform> platforms;
    private List<VideoGameGenre> genres;
    private String picture;
}
