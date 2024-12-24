package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.Success;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISuccessRepository extends JpaRepository<Success, Long> {
}
