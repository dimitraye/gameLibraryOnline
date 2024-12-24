package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Commentary;
import com.gameLibraryOnline.rest.repository.ICommentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaryService {
    @Autowired
    ICommentaryRepository commentaryRepository;


    //Create
    public Commentary save(Commentary user){
        return commentaryRepository.save(user);
    }


    //Show one
    public Optional<Commentary> findById(Long id) {
        return commentaryRepository.findById(id);
    }



    //Show all
    public List<Commentary> findAll() {
        return commentaryRepository.findAll();
    }


    //Update
    public Commentary update(Commentary user){
        return commentaryRepository.save(user);
    }


    //Delete
    public void delete(Long id){
        commentaryRepository.deleteById(id);
    }
}
