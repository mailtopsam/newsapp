package com.stackroute.authservice.config;

import com.stackroute.authservice.model.User;

import java.util.Map;
//interface to generate token
public interface JWTTokenGenerator {
    Map<String, String> generateToken(User user);

}
