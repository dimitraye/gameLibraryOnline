package com.example.service;

import com.example.entity.Success;
import com.example.repository.ISuccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuccessService {

    @Autowired
    ISuccessRepository successRepository;


    //Create
    public Success saveSuccess(Success success){
        return successRepository.save(success);
    }


    //Show one
    public Optional<Success> findSuccessById(Long id) {
        return successRepository.findById(id);
    }



    //Show all
    public List<Success> findAll() {
        return successRepository.findAll();
    }


    //Update
    public Success updateSuccess(Success success){
        return successRepository.save(success);
    }


    //Delete
    public void deleteSuccess(Long id){
         successRepository.deleteById(id);
    }
}
