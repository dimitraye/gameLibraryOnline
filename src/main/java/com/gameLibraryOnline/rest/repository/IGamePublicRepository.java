package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.Game;
import com.gameLibraryOnline.rest.entity.GamePublic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGamePublicRepository extends JpaRepository<GamePublic, Long> {
    Optional<GamePublic> findByTitleIgnoreCase(String title);
}
