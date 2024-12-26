package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.entity.Success;
import com.gameLibraryOnline.rest.service.SuccessService;
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
@RequestMapping("/api/success")
public class SuccessController {
    @Autowired
    SuccessService successService;





    //save
    @PostMapping
    public Success createSuccess(@Validated @RequestBody(required = false) Success success) {
        return successService.save(success);

    }

    //showAll
    @GetMapping
    public List<Success> getAllSuccesss(@Validated @RequestBody(required = false) Success success){
        return successService.findAll();
    }

    //showById
    @GetMapping("/{idSuccess}")
    public ResponseEntity findSuccessById(@PathVariable(name="idSuccess") Long idSuccess) {
        if(idSuccess == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve success with null ID");

        }
        Optional<Success> optionalSuccess = successService.findById(idSuccess);
        if(optionalSuccess == null) {
            return ResponseEntity.notFound().build();

        }
        //return ResponseEntity.ok().build();
        return new ResponseEntity<>(optionalSuccess, HttpStatus.OK);

    }

    //update
    @PutMapping("/{idSuccess}")
    public ResponseEntity<?> updateSuccess(@PathVariable Long idSuccess, @RequestBody Success successRequest) {
        // Rechercher l'utilisateur par ID dans la base de données
        Optional<Success> successFromDb = successService.findById(idSuccess);

        if (successFromDb.isPresent()) {
            // Récupérer l'utilisateur existant
            Success success = successFromDb.get();

            // Mettre à jour les informations de l'utilisateur avec les données de la requête
            success.setDescription(successRequest.getDescription());
            success.setOwned(successRequest.getOwned());

            // Sauvegarder l'utilisateur mis à jour
            successService.save(success);

            return new ResponseEntity<>(Map.of("message", "Success updated successfully!"), HttpStatus.OK);
        }

        // Si l'utilisateur n'existe pas, retourner une erreur 404
        return new ResponseEntity<>(Map.of("error", "Success not found"), HttpStatus.NOT_FOUND);
    }



    //delete
    @DeleteMapping("/{idSuccess}")
    public ResponseEntity<?> deleteSuccess(@PathVariable Long idSuccess) {
        try {
            // Rechercher l'utilisateur par ID
            Optional<Success> successFromDb = successService.findById(idSuccess);

            // Vérifier si l'utilisateur existe
            if (successFromDb.isPresent()) {
                // Supprimer l'utilisateur
                successService.delete(idSuccess);
                return ResponseEntity.ok().body(Map.of("message", "Success deleted successfully!"));
            }

            // Si l'utilisateur n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Success not found"));

        } catch (Exception e) {
            // Si une exception est levée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while deleting the success"));
        }
    }

}
