package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.entity.UserGame;
import com.gameLibraryOnline.rest.repository.IUserGameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserGameServiceTest {

    @InjectMocks
    private UserGameService userGameService;

    @Mock
    private IUserGameRepository userGameRepository;

    private UserGame mockUserGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setIdUser(1L);

        GamePublic gamePublic = new GamePublic();
        gamePublic.setId(2L);

        mockUserGame = new UserGame();
        mockUserGame.setId(10L);
        mockUserGame.setUser(user);
        mockUserGame.setGamePublic(gamePublic);
    }

    @Test
    void testSave_shouldReturnSavedUserGame() {
        when(userGameRepository.save(mockUserGame)).thenReturn(mockUserGame);

        UserGame result = userGameService.save(mockUserGame);

        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    void testFindById_shouldReturnUserGame() {
        when(userGameRepository.findById(10L)).thenReturn(Optional.of(mockUserGame));

        Optional<UserGame> result = userGameService.findById(10L);

        assertTrue(result.isPresent());
        assertEquals(10L, result.get().getId());
    }

    @Test
    void testFindByUser_shouldReturnList() {
        when(userGameRepository.findByUser_IdUser(1L)).thenReturn(List.of(mockUserGame));

        List<UserGame> result = userGameService.findByUser(1L);

        assertEquals(1, result.size());
    }

    @Test
    void testFindByUserAndGamePublic_shouldReturnMatch() {
        when(userGameRepository.findByUser_IdUserAndGamePublic_Id(1L, 2L))
                .thenReturn(Optional.of(mockUserGame));

        Optional<UserGame> result = userGameService.findByUserAndGamePublic(1L, 2L);

        assertTrue(result.isPresent());
    }

    @Test
    void testDelete_shouldCallRepository() {
        doNothing().when(userGameRepository).deleteById(10L);

        userGameService.delete(10L);

        verify(userGameRepository, times(1)).deleteById(10L);
    }
}
