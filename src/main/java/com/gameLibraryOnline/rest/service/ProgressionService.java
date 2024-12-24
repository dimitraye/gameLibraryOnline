package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Progression;
import com.gameLibraryOnline.rest.repository.IProgressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressionService {
    @Autowired
    IProgressionRepository progressionRepository;


    //Create
    public Progression save(Progression progression){
        return progressionRepository.save(progression);
    }


    //Show one
    public Optional<Progression> findById(Long id) {
        return progressionRepository.findById(id);
    }



    //Show all
    public List<Progression> findAll() {
        return progressionRepository.findAll();
    }


    //Update
    public Progression update(Progression progression){
        return progressionRepository.save(progression);
    }


    //Delete
    public void delete(Long id){
        progressionRepository.deleteById(id);
    }
}
