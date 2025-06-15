package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.dto.GamePublicDTO;
import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.service.GamePublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public-games")
public class GamePublicController {

    @Autowired
    private GamePublicService gamePublicService;

    @GetMapping
    public List<GamePublicDTO> getAllPublicGames() {
        List<GamePublic> games = gamePublicService.findAll();
        return games.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private GamePublicDTO toDTO(GamePublic entity) {
        GamePublicDTO dto = new GamePublicDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setGenres(entity.getGenres());
        dto.setPlatforms(entity.getPlatforms());
        dto.setPicture(entity.getPicture());
        return dto;
    }
}
