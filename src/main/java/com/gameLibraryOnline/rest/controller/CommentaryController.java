package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.entity.Commentary;
import com.gameLibraryOnline.rest.service.CommentaryService;
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
@RequestMapping("/api/commentaries")
public class CommentaryController {
    @Autowired
    CommentaryService commentaryService;





    //save
    @PostMapping
    public Commentary createCommentary(@Validated @RequestBody(required = false) Commentary commentary) {
        return commentaryService.save(commentary);

    }

    //showAll
    @GetMapping
    public List<Commentary> getAllCommentarys(@Validated @RequestBody(required = false) Commentary commentary){
        return commentaryService.findAll();
    }

    //showById
    @GetMapping("/{idCommentary}")
    public ResponseEntity findCommentaryById(@PathVariable(name="idCommentary") Long idCommentary) {
        if(idCommentary == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve commentary with null ID");

        }
        Optional<Commentary> optionalCommentary = commentaryService.findById(idCommentary);
        if(optionalCommentary == null) {
            return ResponseEntity.notFound().build();

        }
        //return ResponseEntity.ok().build();
        return new ResponseEntity<>(optionalCommentary, HttpStatus.OK);

    }

    //update
    @PutMapping("/{idCommentary}")
    public ResponseEntity<?> updateCommentary(@PathVariable Long idCommentary, @RequestBody Commentary commentaryRequest) {
        // Rechercher l'utilisateur par ID dans la base de données
        Optional<Commentary> commentaryFromDb = commentaryService.findById(idCommentary);

        if (commentaryFromDb.isPresent()) {
            // Récupérer l'utilisateur existant
            Commentary commentary = commentaryFromDb.get();

            // Mettre à jour les informations de l'utilisateur avec les données de la requête
            commentary.setCommentary(commentaryRequest.getCommentary());
            commentary.setCreationDate(commentaryRequest.getCreationDate());

            // Sauvegarder l'utilisateur mis à jour
            commentaryService.save(commentary);

            return new ResponseEntity<>(Map.of("message", "Commentary updated successfully!"), HttpStatus.OK);
        }

        // Si l'utilisateur n'existe pas, retourner une erreur 404
        return new ResponseEntity<>(Map.of("error", "Commentary not found"), HttpStatus.NOT_FOUND);
    }



    //delete
    @DeleteMapping("/{idCommentary}")
    public ResponseEntity<?> deleteCommentary(@PathVariable Long idCommentary) {
        try {
            // Rechercher l'utilisateur par ID
            Optional<Commentary> commentaryFromDb = commentaryService.findById(idCommentary);

            // Vérifier si l'utilisateur existe
            if (commentaryFromDb.isPresent()) {
                // Supprimer l'utilisateur
                commentaryService.delete(idCommentary);
                return ResponseEntity.ok().body(Map.of("message", "Commentary deleted successfully!"));
            }

            // Si l'utilisateur n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Commentary not found"));

        } catch (Exception e) {
            // Si une exception est levée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while deleting the commentary"));
        }
    }

}
