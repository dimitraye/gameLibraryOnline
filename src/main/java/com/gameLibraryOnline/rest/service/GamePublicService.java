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

    public List<GamePublic> findAll() {
        return gamePublicRepo.findAll();
    }

    public Optional<GamePublic> findById(Long id) {
        return gamePublicRepo.findById(id);
    }

    public GamePublic save(GamePublic game) {
        return gamePublicRepo.save(game);
    }

    public void delete(Long id) {
        gamePublicRepo.deleteById(id);
    }

    public GamePublic findOrCreate(GamePublic game) {
        return gamePublicRepo.findByTitleIgnoreCase(game.getTitle())
                .orElseGet(() -> gamePublicRepo.save(game));
    }

    public GamePublic dtoToEntity(GamePublicDTO dto) {
        GamePublic entity = new GamePublic();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setGenres(dto.getGenres());
        entity.setPlatforms(dto.getPlatforms());
        entity.setPicture(dto.getPicture());
        return entity;
    }

    public GamePublicDTO toDTO(GamePublic entity) {
        GamePublicDTO dto = new GamePublicDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setGenres(entity.getGenres());
        dto.setPlatforms(entity.getPlatforms());
        dto.setPicture(entity.getPicture());
        return dto;
    }

}

