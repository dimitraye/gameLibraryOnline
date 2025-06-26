package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.UserGame;
import com.gameLibraryOnline.rest.repository.IUserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGameService {
    @Autowired
    private IUserGameRepository userGameRepo;

    public UserGame save(UserGame userGame) {

        return userGameRepo.save(userGame);
    }

    public Optional<UserGame> findByUserAndGamePublic(Long userId, Long gamePublicId) {
        return userGameRepo.findByUser_IdUserAndGamePublic_Id(userId, gamePublicId);
    }

    public List<UserGame> findByUser(Long userId) {

        return userGameRepo.findByUser_IdUser(userId);
    }

    public void delete(Long id) {

        userGameRepo.deleteById(id);
    }

    public Optional<UserGame> findById(Long userGameId) {

        return userGameRepo.findById(userGameId);
    }
}

