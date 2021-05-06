package com.stackroute.authservice.controller;

import com.stackroute.authservice.model.User;
import com.stackroute.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserChangeController {

    @Autowired
    UserService service;

    @PostMapping("update")
    public User updateUser(@Valid @RequestBody User user) throws Exception {
        return service.updateUser(user);
    }

    @DeleteMapping("delete")
    public String deleteUser(@RequestParam(value = "id") String email) throws Exception {
        return service.deleteUser(email);
    }

//    @GetMapping("profile")
//    public User getProfile(@RequestParam(value = "email") String email)
//

    @GetMapping("profile")
    public Map<String, Object> getProfile(@RequestParam(value = "email") String email){
        return  service.findById(email);
    }


}