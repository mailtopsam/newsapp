package com.stackroute.authservice.service;

import com.google.common.base.CharMatcher;
import com.stackroute.authservice.exception.UserAlreadyExistsException;
import com.stackroute.authservice.exception.UserNotFoundException;
import com.stackroute.authservice.model.User;
import com.stackroute.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rx.internal.operators.OnSubscribeJoin;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
//indicates annotated class is a service which hold business logic in the Service layer
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    //To get the property values
    @Value("${service.message1}")
    private String message1;

    @Value("${service.message2}")
    private String message2;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    //implements save user method
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {

        Optional<User> userResult = userRepository.findById(user.getId());

        if (userResult.isPresent()) {
            throw new UserAlreadyExistsException(message1);
        }

        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setCountry(user.getCountry());
        newUser.setLanguage(user.getLanguage());
        newUser.setProfileImage(user.getProfileImage());
        return userRepository.save(newUser);
    }

    //implements findByIdAndPassword method
    @Override
    public User findByIdAndPassword(String id, String password) throws UserNotFoundException {

        String encrpytedpassword = passwordEncoder.encode(password);
        User authUser = userRepository.findById(id).get();

        if (!passwordEncoder.matches(password,authUser.getPassword())) {
            throw new UserNotFoundException(message2);
        }
        return authUser;
    }

    @Override
    public User updateUser(User user) throws Exception {
        User user1 = userRepository.findById(user.getId()).get();
        if (user1 == null){
            throw new Exception("User Not Found - Consider Creating an Account");
        }
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setLanguage(user.getLanguage());
        user1.setCountry(user.getCountry());
        user1.setProfileImage(user.getProfileImage());
        return userRepository.save(user1);
    }

    @Override
    public String deleteUser(String email) throws Exception {
        User user1 = userRepository.findById(email).get();
        if (user1 == null){
            throw new Exception("User Not Found to Delete");
        }
        userRepository.delete(user1);
        return "Deleted Account : "+email;
    }

    @Override
    public Map<String, Object> findById(String email) {
        Map<String,Object> response = new HashMap<String, Object>();
        User user1 = userRepository.findById(email).get();
        int len = user1.getPassword().length();
        String replacement = CharMatcher.ANY.replaceFrom(user1.getPassword(),'*');
        response.put("Id",user1.getId());
        response.put("Password",replacement);
        response.put("Firstname",user1.getFirstname() );
        response.put("Lastname",user1.getLastname() );
        response.put("Country",user1.getCountry() );
        response.put("Language",user1.getLanguage() );
        response.put("ProfileImage",user1.getCountry() );
        return response;
    }





}
