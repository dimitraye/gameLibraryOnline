package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Commentary;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.entity.UserGame;
import com.gameLibraryOnline.rest.repository.ICommentaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentaryServiceTest {

    @InjectMocks
    private CommentaryService commentaryService;

    @Mock
    private ICommentaryRepository commentaryRepository;

    private Commentary mockCommentary;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setIdUser(1L);

        UserGame userGame = new UserGame();
        userGame.setId(2L);

        mockCommentary = new Commentary();
        mockCommentary.setIdCommentary(10L);
        mockCommentary.setCommentary("Très bon jeu !");
        mockCommentary.setCreationDate(new Date());
        mockCommentary.setUser(user);
        mockCommentary.setUserGame(userGame);
    }

    @Test
    void testSave_shouldReturnSavedCommentary() {
        when(commentaryRepository.save(mockCommentary)).thenReturn(mockCommentary);

        Commentary result = commentaryService.save(mockCommentary);

        assertNotNull(result);
        assertEquals("Très bon jeu !", result.getCommentary());
        verify(commentaryRepository).save(mockCommentary);
    }

    @Test
    void testFindById_shouldReturnCommentaryOptional() {
        when(commentaryRepository.findById(10L)).thenReturn(Optional.of(mockCommentary));

        Optional<Commentary> result = commentaryService.findById(10L);

        assertTrue(result.isPresent());
        assertEquals(10L, result.get().getIdCommentary());
    }

    @Test
    void testFindAll_shouldReturnList() {
        when(commentaryRepository.findAll()).thenReturn(List.of(mockCommentary));

        List<Commentary> result = commentaryService.findAll();

        assertEquals(1, result.size());
        verify(commentaryRepository).findAll();
    }

    @Test
    void testUpdate_shouldReturnUpdatedCommentary() {
        when(commentaryRepository.save(mockCommentary)).thenReturn(mockCommentary);

        Commentary updated = commentaryService.update(mockCommentary);

        assertEquals(mockCommentary.getCommentary(), updated.getCommentary());
        verify(commentaryRepository).save(mockCommentary);
    }

    @Test
    void testDelete_shouldCallDeleteById() {
        doNothing().when(commentaryRepository).deleteById(10L);

        commentaryService.delete(10L);

        verify(commentaryRepository).deleteById(10L);
    }
}
