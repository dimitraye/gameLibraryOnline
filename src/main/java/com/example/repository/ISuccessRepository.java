package com.example.repository;

import com.example.entity.Success;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISuccessRepository extends JpaRepository<Success, Long> {
}
