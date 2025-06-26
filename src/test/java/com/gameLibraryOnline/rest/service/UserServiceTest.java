package com.gameLibraryOnline.rest.service;

import com.gameLibraryOnline.rest.entity.Role;
import com.gameLibraryOnline.rest.entity.User;
import com.gameLibraryOnline.rest.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User();
        mockUser.setIdUser(1L);
        mockUser.setUsername("john");
        mockUser.setPassword("pass123");
        mockUser.setEmail("john@example.com");
        mockUser.setRole(Role.CLIENT);
    }

    @Test
    void testSave_shouldEncodePasswordAndSaveUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User result = userService.save(mockUser);

        assertNotNull(result);
        verify(passwordEncoder).encode("pass123");
        verify(userRepository).save(mockUser);
    }

    @Test
    void testFindById_shouldReturnUserOptional() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        Optional<User> result = userService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
    }

    @Test
    void testFindByUsername_shouldReturnUserOptional() {
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(mockUser));

        Optional<User> result = userService.findByUsername("john");

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
    }

    @Test
    void testFindAll_shouldReturnUserList() {
        List<User> users = Arrays.asList(mockUser);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void testUpdate_shouldSaveUser() {
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        User result = userService.update(mockUser);

        assertEquals("john", result.getUsername());
        verify(userRepository).save(mockUser);
    }

    @Test
    void testDelete_shouldCallDeleteById() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void testFindByEmail_shouldReturnUserOptional() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(mockUser));

        Optional<User> result = userService.findByEmail("john@example.com");

        assertTrue(result.isPresent());
        assertEquals("john@example.com", result.get().getEmail());
    }
}
