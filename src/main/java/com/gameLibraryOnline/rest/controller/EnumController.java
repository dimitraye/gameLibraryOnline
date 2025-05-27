package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.entity.GameStatus;
import com.gameLibraryOnline.rest.entity.Platform;
import com.gameLibraryOnline.rest.entity.VideoGameGenre;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/platforms")
    public List<Platform> getAllPlatforms() {
        return Arrays.asList(Platform.values());
    }

    @GetMapping("/genres")
    public List<VideoGameGenre> getAllGenres() {
        return Arrays.asList(VideoGameGenre.values());
    }

    @GetMapping("/statuses")
    public List<GameStatus> getAllStatuses() {
        return Arrays.asList(GameStatus.values());
    }
}
