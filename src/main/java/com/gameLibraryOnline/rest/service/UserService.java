package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Injection du PasswordEncoder

    //Create
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Utilisation du passwordEncoder injecté
        return userRepository.save(user);
    }

    //Show one
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //Show all
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //Update
    public User update(User user){
        return userRepository.save(user);
    }

    //Delete
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
