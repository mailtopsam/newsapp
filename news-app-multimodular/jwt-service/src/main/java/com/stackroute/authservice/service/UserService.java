package com.stackroute.authservice.service;


import com.stackroute.authservice.exception.UserAlreadyExistsException;
import com.stackroute.authservice.exception.UserNotFoundException;
import com.stackroute.authservice.model.User;

import java.util.Map;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;

    User findByIdAndPassword(String id, String password) throws UserNotFoundException;

    User updateUser(User user) throws Exception;
    String deleteUser(String email) throws Exception;
//    User findById(String email);
    public Map<String,Object> findById(String email);
}
