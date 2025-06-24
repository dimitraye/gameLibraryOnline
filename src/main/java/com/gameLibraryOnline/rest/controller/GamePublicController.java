package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.dto.GamePublicDTO;
import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.entity.Platform;
import com.gameLibraryOnline.rest.entity.VideoGameGenre;
import com.gameLibraryOnline.rest.service.GamePublicService;
import com.gameLibraryOnline.rest.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.service.ImageStorageService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/public-games")
public class GamePublicController {

    @Autowired
    private GamePublicService gamePublicService;

    @Autowired
    private ImageStorageService imageStorageService;

    // GET all
    @GetMapping
    public List<GamePublicDTO> getAllPublicGames() {
        return gamePublicService.findAll()
                .stream()
                .map(gamePublicService::toDTO)
                .collect(Collectors.toList());
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<GamePublicDTO> getById(@PathVariable Long id) {
        return gamePublicService.findById(id)
                .map(gamePublicService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<GamePublicDTO> create(@RequestBody GamePublicDTO dto) {
        GamePublic saved = gamePublicService.save(gamePublicService.dtoToEntity(dto));
        return ResponseEntity.ok(gamePublicService.toDTO(saved));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<GamePublicDTO> update(@PathVariable Long id, @RequestBody GamePublicDTO dto) {
        return gamePublicService.findById(id)
                .map(existing -> {
                    GamePublic entity = gamePublicService.dtoToEntity(dto);
                    entity.setId(id); // assure l’update de l’existant
                    GamePublic updated = gamePublicService.save(entity);
                    return ResponseEntity.ok(gamePublicService.toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (gamePublicService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        gamePublicService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<GamePublicDTO> uploadGameWithImage(
            @RequestParam("title") String title,
            @RequestParam("platforms") List<String> platformStrings,
            @RequestParam("genres") List<String> genreStrings,
            @RequestParam("picture") MultipartFile pictureFile
    ) {
        try {
            // Convertir les chaînes en énumérations
            List<Platform> platforms = platformStrings.stream()
                    .map(String::toUpperCase)
                    .map(Platform::valueOf)
                    .toList();

            List<VideoGameGenre> genres = genreStrings.stream()
                    .map(String::toUpperCase)
                    .map(VideoGameGenre::valueOf)
                    .toList();

            // Stocker l'image
            String filename = imageStorageService.storeImage(pictureFile, title);

            // Créer le jeu public
            GamePublic game = new GamePublic();
            game.setTitle(title);
            game.setPlatforms(platforms);
            game.setGenres(genres);
            game.setPicture("/pictures/" + filename);

            GamePublic saved = gamePublicService.save(game);
            return ResponseEntity.ok(gamePublicService.toDTO(saved));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Si mauvaise valeur pour un enum
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
