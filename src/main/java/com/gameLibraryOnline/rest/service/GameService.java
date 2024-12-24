package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Game;
import com.gameLibraryOnline.rest.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    IGameRepository gameRepository;


    //Create
    public Game save(Game game){
        return gameRepository.save(game);
    }


    //Show one
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }



    //Show all
    public List<Game> findAll() {
        return gameRepository.findAll();
    }


    //Update
    public Game update(Game game){
        return gameRepository.save(game);
    }


    //Delete
    public void delete(Long id){
        gameRepository.deleteById(id);
    }
}
