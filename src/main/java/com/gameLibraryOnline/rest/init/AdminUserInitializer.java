package com.gameLibraryOnline.rest.init;

import com.gameLibraryOnline.rest.entity.Role;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class AdminUserInitializer {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createAdminIfNotExists() {
        if (userRepository.findByUsername("testuserDeploiement2").isEmpty()) {
            User admin = new User();
            admin.setUsername("testuserDeploiement2");
            admin.setPassword(passwordEncoder.encode("pw123"));
            admin.setFirstname("userDeploiement2");
            admin.setLastname("test");
            admin.setGender(true);
            admin.setEmail("testuser.deploiement2@gmail.com");
            admin.setAdress("1 rue du test deploiement ADMIN");
            admin.setPostCode("86340");
            admin.setCity("Paris");
            admin.setCountry("France");
            admin.setPhoneNumber("0102030405");
            admin.setRole(Role.ADMIN);
            admin.setRegistrationDate(LocalDate.now().toString());
            // Tu peux laisser les autres champs à null
            userRepository.save(admin);
            System.out.println("✅ Utilisateur ADMIN créé.");
        } else {
            System.out.println("ℹ️ Utilisateur ADMIN déjà existant.");
        }
    }
}
