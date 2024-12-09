package com.example.service;

import com.example.entity.Commentary;
import com.example.repository.ICommentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaryService {

    @Autowired
    ICommentaryRepository commentaryRepository;


    //Create
    public Commentary saveCommentary(Commentary user){
        return commentaryRepository.save(user);
    }


    //Show one
    public Optional<Commentary> findCommentaryById(Long id) {
        return commentaryRepository.findById(id);
    }



    //Show all
    public List<Commentary> findAll() {
        return commentaryRepository.findAll();
    }


    //Update
    public Commentary updateCommentary(Commentary user){
        return commentaryRepository.save(user);
    }


    //Delete
    public void deleteCommentary(Long id){
         commentaryRepository.deleteById(id);
    }
}
