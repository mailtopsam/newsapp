package com.stackroute.authservice.config;

import com.stackroute.authservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JWTTokenGeneratorImplTest {
    private User user;

    @Autowired
    private JWTTokenGeneratorImpl jwtTokenGenerator;
    private Map<String, String> tokenMap;

    @BeforeEach
    public void setup() {
        user = new User("John@gmail.com","12323","John","Lin", "India","Hindi","https://bootdey.com/img/Content/avatar/avatar1.png");
        tokenMap = new HashMap<>();
    }

    @Test
    void givenAUserThenCallToGenerateTokenShouldReturnNotNull() {
        tokenMap = jwtTokenGenerator.generateToken(user);
        assertNotNull(tokenMap);
    }

    @Test
    void givenAUserThenShouldReturnExpectedKeysInMap() {
        tokenMap = jwtTokenGenerator.generateToken(user);
        assertNotNull(tokenMap.containsKey("token"));
        assertNotNull(tokenMap.containsKey("message"));
    }

    @Test
    void givenAUserThenShouldReturnExpectedTokenInMap() {
        tokenMap = jwtTokenGenerator.generateToken(user);
        assertThat(tokenMap.get("Token : ").length()).isGreaterThan(20);
    }

    @Test
    void givenAUserThenShouldReturnExpectedClaimInMap() {
        tokenMap = jwtTokenGenerator.generateToken(user);
        assertThat(tokenMap.get("Message : ")).isEqualTo("Login is Successfull. ");
    }
}