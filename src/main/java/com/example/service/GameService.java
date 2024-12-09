package com.example.service;

import com.example.entity.Game;
import com.example.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    IGameRepository gameRepository;


    //Create
    public Game saveGame(Game game){
        return gameRepository.save(game);
    }


    //Show one
    public Optional<Game> findGameById(Long id) {
        return gameRepository.findById(id);
    }



    //Show all
    public List<Game> findAll() {
        return gameRepository.findAll();
    }


    //Update
    public Game updateGame(Game game){
        return gameRepository.save(game);
    }


    //Delete
    public void deleteGame(Long id){
         gameRepository.deleteById(id);
    }
}
