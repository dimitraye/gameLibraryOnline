package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Success;
import com.gameLibraryOnline.rest.repository.ISuccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuccessService {
    @Autowired
    ISuccessRepository successRepository;


    //Create
    public Success save(Success success){
        return successRepository.save(success);
    }


    //Show one
    public Optional<Success> findById(Long id) {
        return successRepository.findById(id);
    }



    //Show all
    public List<Success> findAll() {
        return successRepository.findAll();
    }

    public List<Success> get5SuccessById(Long userId) {
        Pageable pageable = PageRequest.of(0, 5);
        return successRepository.findTopByUserId(userId, pageable);
    }




    //Update
    public Success update(Success success){
        return successRepository.save(success);
    }


    //Delete
    public void delete(Long id){
        successRepository.deleteById(id);
    }
}
