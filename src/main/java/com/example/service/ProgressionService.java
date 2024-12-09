package com.example.service;

import com.example.entity.Progression;
import com.example.repository.IProgressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressionService {

    @Autowired
    IProgressionRepository progressionRepository;


    //Create
    public Progression saveProgression(Progression progression){
        return progressionRepository.save(progression);
    }


    //Show one
    public Optional<Progression> findProgressionById(Long id) {
        return progressionRepository.findById(id);
    }



    //Show all
    public List<Progression> findAll() {
        return progressionRepository.findAll();
    }


    //Update
    public Progression updateProgression(Progression progression){
        return progressionRepository.save(progression);
    }


    //Delete
    public void deleteProgression(Long id){
         progressionRepository.deleteById(id);
    }
}
