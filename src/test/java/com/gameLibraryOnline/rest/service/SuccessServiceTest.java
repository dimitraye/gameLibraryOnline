package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Success;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.entity.UserGame;
import com.gameLibraryOnline.rest.repository.ISuccessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class SuccessServiceTest {

    @InjectMocks
    private SuccessService successService;

    @Mock
    private ISuccessRepository successRepository;

    private Success mockSuccess;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setIdUser(1L);

        UserGame userGame = new UserGame();
        userGame.setId(2L);

        mockSuccess = new Success();
        mockSuccess.setIdSuccess(10L);
        mockSuccess.setDescription("Trophée débloqué");
        mockSuccess.setOwned(true);
        mockSuccess.setUser(user);
        mockSuccess.setUserGame(userGame);
    }

    @Test
    void testSave_shouldReturnSavedSuccess() {
        when(successRepository.save(mockSuccess)).thenReturn(mockSuccess);

        Success result = successService.save(mockSuccess);

        assertNotNull(result);
        assertEquals(10L, result.getIdSuccess());
        verify(successRepository).save(mockSuccess);
    }

    @Test
    void testFindById_shouldReturnSuccessOptional() {
        when(successRepository.findById(10L)).thenReturn(Optional.of(mockSuccess));

        Optional<Success> result = successService.findById(10L);

        assertTrue(result.isPresent());
        assertEquals("Trophée débloqué", result.get().getDescription());
    }

    @Test
    void testFindAll_shouldReturnList() {
        when(successRepository.findAll()).thenReturn(List.of(mockSuccess));

        List<Success> result = successService.findAll();

        assertEquals(1, result.size());
        verify(successRepository).findAll();
    }

    @Test
    void testGet5SuccessById_shouldCallCustomQuery() {
        Long userId = 1L;
        List<Success> expectedList = List.of(mockSuccess);
        Pageable pageable = PageRequest.of(0, 5);

        when(successRepository.findTopByUserId(userId, pageable)).thenReturn(expectedList);

        List<Success> result = successService.get5SuccessById(userId);

        assertEquals(1, result.size());
        verify(successRepository).findTopByUserId(eq(userId), eq(pageable));
    }

    @Test
    void testUpdate_shouldSaveAgain() {
        when(successRepository.save(mockSuccess)).thenReturn(mockSuccess);

        Success updated = successService.update(mockSuccess);

        assertEquals("Trophée débloqué", updated.getDescription());
        verify(successRepository).save(mockSuccess);
    }

    @Test
    void testDelete_shouldCallDeleteById() {
        doNothing().when(successRepository).deleteById(10L);

        successService.delete(10L);

        verify(successRepository).deleteById(10L);
    }
}
