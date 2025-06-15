package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.dto.GamePublicDTO;
import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.entity.UserGame;
import com.gameLibraryOnline.rest.repository.IGamePublicRepository;
import com.gameLibraryOnline.rest.repository.IUserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamePublicService {
    @Autowired
    private IGamePublicRepository gamePublicRepo;

    public GamePublic findOrCreate(GamePublic game) {
        return gamePublicRepo.findByTitleIgnoreCase(game.getTitle())
                .orElseGet(() -> gamePublicRepo.save(game));
    }

    public List<GamePublic> findAll() {
        return gamePublicRepo.findAll();
    }

    public GamePublic dtoToEntity(GamePublicDTO dto) {
        GamePublic entity = new GamePublic();
        entity.setId(dto.getId()); // facultatif si tu ne veux pas l’écraser
        entity.setTitle(dto.getTitle());
        entity.setGenres(dto.getGenres());
        entity.setPlatforms(dto.getPlatforms());
        entity.setPicture(dto.getPicture());
        return entity;
    }

}

