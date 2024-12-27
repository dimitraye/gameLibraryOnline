package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Game;
import com.gameLibraryOnline.rest.entity.Progression;
import com.gameLibraryOnline.rest.entity.Statistic;
import com.gameLibraryOnline.rest.repository.IStatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    IStatisticsRepository statisticsRepository;
    GameService gameService;


    //Create
    public Statistic save(Statistic statistic){
        return statisticsRepository.save(statistic);
    }

    //CALCULATE nbTotalGames


    //CALCULATE nbGamesNotStarted


    //CALCULATE ngGamesFinished


    //CALCULATE nbGamesStarted


    //CALCULATE nbGamesByPlateform


    //CALCULATE nbGamesByGenre
}
