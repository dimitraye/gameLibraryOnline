package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.dto.GamePublicDTO;
import com.gameLibraryOnline.rest.entity.GamePublic;
import com.gameLibraryOnline.rest.repository.IGamePublicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GamePublicServiceTest {

    @InjectMocks
    private GamePublicService gamePublicService;

    @Mock
    private IGamePublicRepository gamePublicRepository;

    private GamePublic sampleGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleGame = new GamePublic();
        sampleGame.setId(1L);
        sampleGame.setTitle("Zelda");
    }

    @Test
    void testSave_shouldReturnSavedGame() {
        when(gamePublicRepository.save(sampleGame)).thenReturn(sampleGame);

        GamePublic result = gamePublicService.save(sampleGame);

        assertNotNull(result);
        assertEquals("Zelda", result.getTitle());
    }

    @Test
    void testFindById_shouldReturnGame() {
        when(gamePublicRepository.findById(1L)).thenReturn(Optional.of(sampleGame));

        Optional<GamePublic> result = gamePublicService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindAll_shouldReturnGameList() {
        when(gamePublicRepository.findAll()).thenReturn(List.of(sampleGame));

        List<GamePublic> result = gamePublicService.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void testDelete_shouldCallRepository() {
        doNothing().when(gamePublicRepository).deleteById(1L);

        gamePublicService.delete(1L);

        verify(gamePublicRepository, times(1)).deleteById(1L);
    }
}
