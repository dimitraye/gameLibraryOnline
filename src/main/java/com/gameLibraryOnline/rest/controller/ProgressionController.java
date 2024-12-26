package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.entity.Progression;
import com.gameLibraryOnline.rest.service.ProgressionService;
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
@RequestMapping("/api/progression")
public class ProgressionController {
    @Autowired
    ProgressionService progressionService;





    //save
    @PostMapping
    public Progression createProgression(@Validated @RequestBody(required = false) Progression progression) {
        return progressionService.save(progression);

    }

    //showAll
    @GetMapping
    public List<Progression> getAllProgressions(@Validated @RequestBody(required = false) Progression progression){
        return progressionService.findAll();
    }

    //showById
    @GetMapping("/{idProgression}")
    public ResponseEntity findProgressionById(@PathVariable(name="idProgression") Long idProgression) {
        if(idProgression == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve progression with null ID");

        }
        Optional<Progression> optionalProgression = progressionService.findById(idProgression);
        if(optionalProgression == null) {
            return ResponseEntity.notFound().build();

        }
        //return ResponseEntity.ok().build();
        return new ResponseEntity<>(optionalProgression, HttpStatus.OK);

    }

    //update
    @PutMapping("/{idProgression}")
    public ResponseEntity<?> updateProgression(@PathVariable Long idProgression, @RequestBody Progression progressionRequest) {
        // Rechercher l'utilisateur par ID dans la base de données
        Optional<Progression> progressionFromDb = progressionService.findById(idProgression);

        if (progressionFromDb.isPresent()) {
            // Récupérer l'utilisateur existant
            Progression progression = progressionFromDb.get();

            // Mettre à jour les informations de l'utilisateur avec les données de la requête
            progression.setDetailsProgression(progressionRequest.getDetailsProgression());
            progression.setPercentageCompletion(progressionRequest.getPercentageCompletion());

            // Sauvegarder l'utilisateur mis à jour
            progressionService.save(progression);

            return new ResponseEntity<>(Map.of("message", "Progression updated successfully!"), HttpStatus.OK);
        }

        // Si l'utilisateur n'existe pas, retourner une erreur 404
        return new ResponseEntity<>(Map.of("error", "Progression not found"), HttpStatus.NOT_FOUND);
    }



    //delete
    @DeleteMapping("/{idProgression}")
    public ResponseEntity<?> deleteProgression(@PathVariable Long idProgression) {
        try {
            // Rechercher l'utilisateur par ID
            Optional<Progression> progressionFromDb = progressionService.findById(idProgression);

            // Vérifier si l'utilisateur existe
            if (progressionFromDb.isPresent()) {
                // Supprimer l'utilisateur
                progressionService.delete(idProgression);
                return ResponseEntity.ok().body(Map.of("message", "Progression deleted successfully!"));
            }

            // Si l'utilisateur n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Progression not found"));

        } catch (Exception e) {
            // Si une exception est levée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while deleting the progression"));
        }
    }

}
