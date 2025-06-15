package com.gameLibraryOnline.rest.util;

import com.gameLibraryOnline.rest.dto.GamePublicDTO;
import com.gameLibraryOnline.rest.dto.UserGameDTO;
import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.entity.UserGame;

public class UserGameMapper {

    public static UserGameDTO toDTO(UserGame entity) {
        UserGameDTO dto = new UserGameDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getIdUser());
        dto.setGamePublicId(entity.getGamePublic().getId());
        dto.setDatePurchase(entity.getDatePurchase());
        dto.setPlayHours(entity.getPlayHours());
        dto.setState(entity.getState());

        GamePublic gamePublic = entity.getGamePublic();
        GamePublicDTO gameDto = new GamePublicDTO();
        gameDto.setId(gamePublic.getId());
        gameDto.setTitle(gamePublic.getTitle());
        gameDto.setGenres(gamePublic.getGenres());
        gameDto.setPlatforms(gamePublic.getPlatforms());
        gameDto.setPicture(gamePublic.getPicture());

        dto.setGamePublic(gameDto);
        return dto;
    }

    public static UserGame toEntity(User user, GamePublic gamePublic, UserGameDTO dto) {
        UserGame entity = new UserGame();
        entity.setUser(user);
        entity.setGamePublic(gamePublic);
        entity.setDatePurchase(dto.getDatePurchase());
        entity.setPlayHours(dto.getPlayHours());
        entity.setState(dto.getState());
        return entity;
    }
}
