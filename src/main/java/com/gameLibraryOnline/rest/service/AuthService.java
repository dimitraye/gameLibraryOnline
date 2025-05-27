package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.dto.AuthResponse;
import com.gameLibraryOnline.rest.dto.LoginRequest;
import com.gameLibraryOnline.rest.dto.RegisterRequest;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.entity.Role;
import com.gameLibraryOnline.rest.repository.IUserRepository;
import com.gameLibraryOnline.rest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.username).isPresent()) {
            return new AuthResponse("Nom d'utilisateur déjà pris", false, null);
        }

        User user = new User();
        user.setUsername(request.username);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setFirstname(request.firstname);
        user.setLastname(request.lastname);
        user.setEmail(request.email);
        user.setBirthDate(request.birthDate);
        user.setGender(request.gender);
        user.setAdress(request.adress);
        user.setPostCode(request.postCode);
        user.setCity(request.city);
        user.setCountry(request.country);
        user.setPhoneNumber(request.phoneNumber);
        user.setRegistrationDate(LocalDate.now().toString());
        user.setRole(Role.CLIENT);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail()); // ou user.getUsername() selon le `UserDetailsService`

        return new AuthResponse("Inscription réussie", true, token);
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.username);
        if (optionalUser.isEmpty()) {
            return new AuthResponse("Utilisateur introuvable", false, null);
        }

        User user = optionalUser.get();
        if (passwordEncoder.matches(request.password, user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail()); // ou user.getUsername()
            return new AuthResponse("Connexion réussie", true, token);
        } else {
            return new AuthResponse("Mot de passe incorrect", false, null);
        }
    }
}
