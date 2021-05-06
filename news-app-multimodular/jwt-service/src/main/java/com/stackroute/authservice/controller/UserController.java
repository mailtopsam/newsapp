package com.stackroute.authservice.controller;

import com.stackroute.authservice.config.JWTTokenGenerator;
import com.stackroute.authservice.exception.UserAlreadyExistsException;
import com.stackroute.authservice.exception.UserNotFoundException;
import com.stackroute.authservice.model.User;
import com.stackroute.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//annotation  used to create Restful web services using Spring MVC
@RestController
//annotation to add logging
@Slf4j
public class UserController {

    private UserService userService;
    private JWTTokenGenerator jwtTokenGenerator;

    ResponseEntity<?> responseEntity;
    //get the property values
    @Value("${controller.exception.message1}")
    private String message1;

    @Value("${controller.exception.message2}")
    private String message2;

    @Value("${controller.exception.message3}")
    private String message3;


    @Autowired
    public UserController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    /**
     * If registered successfully returning status as Created (201) ,
     * Otherwise returning status as Conflict
     * This handler method maps to "/api/v1/signup" using HTTP POST*/
    @PostMapping("signup")
    public ResponseEntity<?> registerUser( @Valid @RequestBody User user) {
        try {
            log.info("saveUser service is called to create a new user account");
            User savedUser = userService.saveUser(user);
            responseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            log.error("User Already Exists exception found" + e.getMessage());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    /**
     * Id and Password is validated before token generation
     * If credentials are validated, token is generated
     * otherwise exception is thrown
     * This handler method maps to"login" using HTTP POST*/

    @PostMapping("login")
    public ResponseEntity<?> loginUser( @RequestBody User user) {
        try {
            if (user.getId() == null || user.getPassword() == null) {
                log.error("User Not Found Exception" + message1);
                throw new UserNotFoundException(message1);
            }

            User userDetails = userService.findByIdAndPassword(user.getId(), user.getPassword());

            if (userDetails == null) {
                log.error("User Not Found Exception" + message2);
                throw new UserNotFoundException(message2);
            }

            responseEntity = new ResponseEntity<>(jwtTokenGenerator.generateToken(userDetails),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.error("User Not Found Exception found");
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /**
     * To handle invalid values of arguments
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

