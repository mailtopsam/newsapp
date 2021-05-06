package com.stackroute.authservice.service;

import com.stackroute.authservice.exception.UserAlreadyExistsException;
import com.stackroute.authservice.exception.UserNotFoundException;
import com.stackroute.authservice.model.User;
import com.stackroute.authservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserServiceTest {
    @Mock
    private UserRepository repository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl service;


    private User user;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User("anjali@ust.com","234lads","Anjali","Ananth","India","english","https://bootdey.com/img/Content/avatar/avatar3.png");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

//    @Test
//    void givenUserProfileToSaveThenShouldReturnSavedUser() throws UserAlreadyExistsException {
//        when(repository.save(any())).thenReturn(user);
//        when(passwordEncoder.encode(any())).thenReturn("asd");
//        assertEquals(user, service.saveUser(user));
//        verify(repository, times(1)).save(any());
//    }
    @Test
    public void givenProfileToSaveThenShouldNotReturnSavedProfile() {
        when(repository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class,() -> {
            service.saveUser(user);
        });

    }

//    @Test
//    void givenIdAndPasswordReturnUserProfile() throws UserNotFoundException {
//        when(repository.findByIdAndPassword(any(),any())).thenReturn(user);
//        when(passwordEncoder.encode(any())).thenReturn("asd");
//        User user =  service.findByIdAndPassword(any(),any());
//        verify(repository, times(1)).findByIdAndPassword("anjali@ust.com","234lads");
//    }

}