package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Progression;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.entity.UserGame;
import com.gameLibraryOnline.rest.repository.IProgressionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgressionServiceTest {

    @InjectMocks
    private ProgressionService progressionService;

    @Mock
    private IProgressionRepository progressionRepository;

    private Progression mockProgression;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setIdUser(1L);

        UserGame userGame = new UserGame();
        userGame.setId(2L);

        mockProgression = new Progression();
        mockProgression.setIdProgression(10L);
        mockProgression.setDetailsProgression("Débuté le chapitre 1");
        mockProgression.setPercentageCompletion(20);
        mockProgression.setUser(user);
        mockProgression.setUserGame(userGame);
    }

    @Test
    void testSave_shouldReturnSavedProgression() {
        when(progressionRepository.save(mockProgression)).thenReturn(mockProgression);

        Progression result = progressionService.save(mockProgression);

        assertNotNull(result);
        assertEquals(10L, result.getIdProgression());
        verify(progressionRepository, times(1)).save(mockProgression);
    }

    @Test
    void testFindById_shouldReturnProgressionOptional() {
        when(progressionRepository.findById(10L)).thenReturn(Optional.of(mockProgression));

        Optional<Progression> result = progressionService.findById(10L);

        assertTrue(result.isPresent());
        assertEquals("Débuté le chapitre 1", result.get().getDetailsProgression());
    }

    @Test
    void testFindAll_shouldReturnList() {
        when(progressionRepository.findAll()).thenReturn(List.of(mockProgression));

        List<Progression> result = progressionService.findAll();

        assertEquals(1, result.size());
        verify(progressionRepository, times(1)).findAll();
    }

    @Test
    void testUpdate_shouldReturnUpdatedProgression() {
        when(progressionRepository.save(mockProgression)).thenReturn(mockProgression);

        Progression result = progressionService.update(mockProgression);

        assertEquals(mockProgression.getDetailsProgression(), result.getDetailsProgression());
        verify(progressionRepository).save(mockProgression);
    }

    @Test
    void testDelete_shouldCallDeleteById() {
        doNothing().when(progressionRepository).deleteById(10L);

        progressionService.delete(10L);

        verify(progressionRepository).deleteById(10L);
    }
}
