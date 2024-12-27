package com.gameLibraryOnline.rest.repository;

import com.gameLibraryOnline.rest.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatisticsRepository extends JpaRepository<Statistic, Long> {
}
