package com.stackroute.authservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authservice.exception.UserAlreadyExistsException;
import com.stackroute.authservice.model.User;
import com.stackroute.authservice.service.UserService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService service;
    private MockMvc mockMvc;

    @InjectMocks
    private UserController controller;
    private User user = new User();


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        user.setId("anjali@ust.com");
        user.setPassword("234lads5678");
        user.setFirstname("Anjali");
        user.setLastname("Ananth");
        user.setCountry("India");
        user.setLanguage("English");
        user.setProfileImage("https://bootdey.com/img/Content/avatar/avatar3.png");

    }

// Constraint Violation Tests

    @Test
    public void whenNotEmptyName_thenNoConstraintViolations() {
        User user = new User("anjali@ust.com","234lads5678","Anjali","Ananth","India","english","https://bootdey.com/img/Content/avatar/avatar3.png");
        Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        assertEquals(0,violations.size());
    }

    @Test
    public void whenPasswordIsTooSmall_thenOneConstraintViolation() {
        User user = new User("anjali@ust.com","234lads","Anjali","Ananth","India","english","https://bootdey.com/img/Content/avatar/avatar3.png");
        Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        assertEquals(1,violations.size());
    }

    @Test
    public void whenEmptyId_thenConstraintViolations() {
        User user = new User("","234lads5678","Anjali","Ananth","India","english","https://bootdey.com/img/Content/avatar/avatar3.png");
        Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        assertEquals(1,violations.size());
    }

    @Test
    public void whenEmptyPassword_thenConstraintViolations() {
        User user = new User("Anjali@ust.com","","Anjali","Ananth","India","english","https://bootdey.com/img/Content/avatar/avatar3.png");
        Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        assertEquals(2,violations.size());
    }

    @Test
    public void whenNullProfileImage_thenConstraintViolations() {
        User user = new User("Anjali@ust.com","12345678","Anjali","Ananth","India","english",null);
        Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        assertEquals(1,violations.size());
    }

    @Test
    public void whenNullId_thenConstraintViolations() {
        User user = new User(null,"12345678","Anjali","Ananth","India","english","https://bootdey.com/img/Content/avatar/avatar3.png");
        Set<ConstraintViolation<User>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        assertEquals(1,violations.size());
    }

    @Test
    public void givenUserProfileToSignUpThenShouldReturnRegisteredProfile() throws Exception {
        when(service.saveUser(any())).thenReturn(user);
        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
        verify(service,times(1)).saveUser(any());
    }

    @Test
    void givenUserIdAndPasswordThenShouldValidateTheProfile() throws Exception {
        when(service.findByIdAndPassword(user.getId(), user.getPassword())).thenReturn(user);
        mockMvc.perform(get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}