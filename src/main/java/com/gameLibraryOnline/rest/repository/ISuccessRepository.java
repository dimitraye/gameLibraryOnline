package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.Success;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@Repository
public interface ISuccessRepository extends JpaRepository<Success, Long> {

    @Query("SELECT s FROM Success s WHERE s.user.id = :userId AND s.owned = true ORDER BY s.id DESC")
    List<Success> findTopByUserId(@Param("userId") Long userId, Pageable pageable);

}
