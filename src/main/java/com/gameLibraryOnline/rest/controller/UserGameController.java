package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.dto.GamePublicDTO;
import com.gameLibraryOnline.rest.dto.UserGameDTO;
import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.entity.UserGame;
import com.gameLibraryOnline.rest.service.GamePublicService;
import com.gameLibraryOnline.rest.service.UserGameService;
import com.gameLibraryOnline.rest.service.UserService;
import com.gameLibraryOnline.rest.util.UserGameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/user-games")
public class UserGameController {

    @Autowired
    private UserGameService userGameService;

    @Autowired
    private GamePublicService gamePublicService;

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addGameToUser(@PathVariable Long userId, @RequestBody GamePublicDTO publicGameDto) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }

        GamePublic publicGame = gamePublicService.findOrCreate(
                gamePublicService.dtoToEntity(publicGameDto)
        );

        Optional<UserGame> existing = userGameService.findByUserAndGamePublic(userId, publicGame.getId());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Ce jeu est déjà dans votre bibliothèque");
        }

        UserGame newEntry = new UserGame();
        newEntry.setUser(userOpt.get());
        newEntry.setGamePublic(publicGame);
        newEntry.setPlayHours(0);
        newEntry.setState(null);

        UserGame saved = userGameService.save(newEntry);
        return ResponseEntity.ok(UserGameMapper.toDTO(saved));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserGameDTO>> getUserGames(@PathVariable Long userId) {
        List<UserGame> userGames = userGameService.findByUser(userId);
        List<UserGameDTO> dtos = userGames.stream()
                .map(UserGameMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<List<UserGameDTO>> getCurrentUserGames(Principal principal) {
        String username = principal.getName();
        Optional<User> userOpt = userService.findByUsername(username);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body(null);
        }

        List<UserGame> userGames = userGameService.findByUser(userOpt.get().getIdUser());
        List<UserGameDTO> dtos = userGames.stream()
                .map(UserGameMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/detail/{userGameId}")
    public ResponseEntity<?> getUserGameById(@PathVariable Long userGameId) {
        Optional<UserGame> userGameOpt = userGameService.findById(userGameId);

        if (userGameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserGameDTO dto = UserGameMapper.toDTO(userGameOpt.get());
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{userGameId}")
    public ResponseEntity<?> updateUserGame(@PathVariable Long userGameId, @RequestBody UserGameDTO updateDto) {
        Optional<UserGame> opt = userGameService.findById(userGameId);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserGame existing = opt.get();
        existing.setDatePurchase(updateDto.getDatePurchase());
        existing.setPlayHours(updateDto.getPlayHours());
        existing.setState(updateDto.getState());

        return ResponseEntity.ok(UserGameMapper.toDTO(userGameService.save(existing)));
    }

    @DeleteMapping("/{userGameId}")
    public ResponseEntity<?> deleteUserGame(@PathVariable Long userGameId) {
        userGameService.delete(userGameId);
        return ResponseEntity.ok(Map.of("message", "Jeu supprimé de la bibliothèque"));
    }
}
