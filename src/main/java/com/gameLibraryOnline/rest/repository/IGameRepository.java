package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {
}
