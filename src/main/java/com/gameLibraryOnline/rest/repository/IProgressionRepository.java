package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.Progression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProgressionRepository extends JpaRepository<Progression, Long> {
}
