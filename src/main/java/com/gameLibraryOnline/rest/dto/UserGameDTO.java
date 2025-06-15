package com.gameLibraryOnline.rest.dto;

import com.gameLibraryOnline.rest.entity.GameStatus;
import lombok.Data;

import java.util.Date;

@Data
public class UserGameDTO {
    private Long id;
    private Long userId;
    private Long gamePublicId;

    private Date datePurchase;
    private int playHours;
    private GameStatus state;

    private GamePublicDTO gamePublic;
}
