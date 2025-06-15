package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserGameRepository extends JpaRepository<UserGame, Long> {
    List<UserGame> findByUser_IdUser(Long userId);
    Optional<UserGame> findByUser_IdUserAndGamePublic_Id(Long userId, Long gamePublicId);
}
