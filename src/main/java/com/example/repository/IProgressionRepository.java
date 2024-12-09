package com.example.repository;

import com.example.entity.Progression;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProgressionRepository extends JpaRepository<Progression, Long> {
}
