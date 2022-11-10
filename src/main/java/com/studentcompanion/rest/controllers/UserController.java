package com.studentcompanion.rest.controllers;

import com.google.gson.Gson;
import com.studentcompanion.rest.models.*;
import com.studentcompanion.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    TokenRepository tokenRepository;

    @GetMapping("/users")
    public List <UserDTO> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/generate")
    public Token generateToken() {

        Token token = new Token(UUID.randomUUID().toString());

        return token;
    }

    @GetMapping("/get-by-username/{username}")
    public User getUserByUsername(@PathVariable String username){return userService.findUserByUsername(username);}

    @GetMapping("/get-courses-by-username/{username}")
    public List<Course> getCoursesByUsername(@PathVariable String username){return userService.findCoursesByUsername(username);}

    @GetMapping("/getTokens")
    public List<Token> getAllTokens (){return userService.getTokens();}

    @PutMapping("/updateToken")
    public User updateUserToken(@RequestBody User user, String token){
        return userService.updateUserToken(user, token);
    }

}
