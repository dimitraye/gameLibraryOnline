package com.gameLibraryOnline.rest.controller;

import com.gameLibraryOnline.rest.entity.Role;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;





    //save
    @PostMapping
    public User createUser(@Validated @RequestBody(required = false) User user) {
        return userService.save(user);

    }

    //showAll
    @GetMapping
    public List<User> getAllUsers(@Validated @RequestBody(required = false) User user){
        return userService.findAll();
    }

    //showById
    @GetMapping("/{idUser}")
    public ResponseEntity findUserById(@PathVariable(name="idUser") Long idUser) {
        if(idUser == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve user with null ID");

        }
        Optional<User> optionalUser = userService.findById(idUser);
        if(optionalUser == null) {
            return ResponseEntity.notFound().build();

        }
        //return ResponseEntity.ok().build();
        return new ResponseEntity<>(optionalUser, HttpStatus.OK);

    }

    //update
    @PutMapping("/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable Long idUser, @RequestBody User userRequest) {
        // Rechercher l'utilisateur par ID dans la base de données
        Optional<User> userFromDb = userService.findById(idUser);

        if (userFromDb.isPresent()) {
            // Récupérer l'utilisateur existant
            User user = userFromDb.get();

            // Mettre à jour les informations de l'utilisateur avec les données de la requête
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setFirstname(userRequest.getFirstname());
            user.setLastname(userRequest.getLastname());
            user.setBirthDate(userRequest.getBirthDate());
            user.setGender(userRequest.getGender());
            user.setAdress(userRequest.getAdress());
            user.setPostCode(userRequest.getPostCode());
            user.setCity(userRequest.getCity());
            user.setCountry(userRequest.getCountry());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setEmail(userRequest.getEmail());
            user.setRole(userRequest.getRole());

            // Sauvegarder l'utilisateur mis à jour
            userService.save(user);

            return new ResponseEntity<>(Map.of("message", "User updated successfully!"), HttpStatus.OK);
        }

        // Si l'utilisateur n'existe pas, retourner une erreur 404
        return new ResponseEntity<>(Map.of("error", "User not found"), HttpStatus.NOT_FOUND);
    }



    //delete
    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser) {
        try {
            // Rechercher l'utilisateur par ID
            Optional<User> userFromDb = userService.findById(idUser);

            // Vérifier si l'utilisateur existe
            if (userFromDb.isPresent()) {
                // Supprimer l'utilisateur
                userService.delete(idUser);
                return ResponseEntity.ok().body(Map.of("message", "User deleted successfully!"));
            }

            // Si l'utilisateur n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));

        } catch (Exception e) {
            // Si une exception est levée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while deleting the user"));
        }
    }

}
