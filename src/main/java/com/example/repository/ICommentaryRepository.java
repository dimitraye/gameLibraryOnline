package com.example.repository;

import com.example.entity.Commentary;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentaryRepository extends JpaRepository<Commentary, Long> {
}
