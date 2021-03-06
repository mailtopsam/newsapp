package com.stackroute.authservice.config;

import com.stackroute.authservice.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



//@Service indicates annotated class is a service which hold business logic in the Service layer
@Service
//This class is implementing the JWTTokenGenerator interface. This class has to be annotated with @Service annotation
public class JWTTokenGeneratorImpl  implements JWTTokenGenerator {

     // To get the property values
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwttoken.message}")
    private String message;

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken = "";

         //Generate JWT token and store in String jwtToken
        jwtToken= Jwts.builder().setSubject(user.getId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secret).compact();
        Map<String, String> jwtTokenMap= new HashMap<>();
        jwtTokenMap.put("Token : ",jwtToken);
        jwtTokenMap.put("Message : ",message);
        return jwtTokenMap;
    }
}
