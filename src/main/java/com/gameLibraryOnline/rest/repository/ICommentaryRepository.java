package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentaryRepository extends JpaRepository<Commentary, Long> {
}
