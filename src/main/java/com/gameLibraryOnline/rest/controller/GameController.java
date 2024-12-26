package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.entity.Game;
import com.gameLibraryOnline.rest.service.GameService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    GameService gameService;





    //save
    @PostMapping
    public Game createGame(@Validated @RequestBody(required = false) Game game) {
        return gameService.save(game);

    }

    //showAll
    @GetMapping
    public List<Game> getAllGames(@Validated @RequestBody(required = false) Game game){
        return gameService.findAll();
    }

    //showById
    @GetMapping("/{idGame}")
    public ResponseEntity findGameById(@PathVariable(name="idGame") Long idGame) {
        if(idGame == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve game with null ID");

        }
        Optional<Game> optionalGame = gameService.findById(idGame);
        if(optionalGame == null) {
            return ResponseEntity.notFound().build();

        }
        //return ResponseEntity.ok().build();
        return new ResponseEntity<>(optionalGame, HttpStatus.OK);

    }

    //update
    @PutMapping("/{idGame}")
    public ResponseEntity<?> updateGame(@PathVariable Long idGame, @RequestBody Game gameRequest) {
        // Rechercher l'utilisateur par ID dans la base de données
        Optional<Game> gameFromDb = gameService.findById(idGame);

        if (gameFromDb.isPresent()) {
            // Récupérer l'utilisateur existant
            Game game = gameFromDb.get();

            // Mettre à jour les informations de l'utilisateur avec les données de la requête
            game.setTitle(gameRequest.getTitle());
            game.setPlatform(gameRequest.getPlatform());
            game.setVideoGameGenre(gameRequest.getVideoGameGenre());
            game.setDatePurchase(gameRequest.getDatePurchase());
            game.setPlayHours(gameRequest.getPlayHours());
            game.setState(gameRequest.getState());
            game.setPicture(gameRequest.getPicture());

            // Sauvegarder l'utilisateur mis à jour
            gameService.save(game);

            return new ResponseEntity<>(Map.of("message", "Game updated successfully!"), HttpStatus.OK);
        }

        // Si l'utilisateur n'existe pas, retourner une erreur 404
        return new ResponseEntity<>(Map.of("error", "Game not found"), HttpStatus.NOT_FOUND);
    }



    //delete
    @DeleteMapping("/{idGame}")
    public ResponseEntity<?> deleteGame(@PathVariable Long idGame) {
        try {
            // Rechercher l'utilisateur par ID
            Optional<Game> gameFromDb = gameService.findById(idGame);

            // Vérifier si l'utilisateur existe
            if (gameFromDb.isPresent()) {
                // Supprimer l'utilisateur
                gameService.delete(idGame);
                return ResponseEntity.ok().body(Map.of("message", "Game deleted successfully!"));
            }

            // Si l'utilisateur n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Game not found"));

        } catch (Exception e) {
            // Si une exception est levée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while deleting the game"));
        }
    }

}
