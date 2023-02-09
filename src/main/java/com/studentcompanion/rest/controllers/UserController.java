package com.studentcompanion.rest.controllers;

import com.studentcompanion.rest.models.*;
import com.studentcompanion.rest.services.UserService;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {


    @Autowired
    UserService userService;
    TokenRepository tokenRepository;

    public UserController(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    boolean auth = false;

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

    @PostMapping("/updateToken")
    public User handlePostRequest(@RequestBody String requestBody) {
        //String responseBody = "OK";
        Object o1 = JSONValue.parse(requestBody);
        JSONObject jsonObj = (JSONObject) o1;
        String userID = (String) jsonObj.get("id");
        String token = (String) jsonObj.get("token");

        return userService.updateUserToken(Integer.parseInt(userID), token);
    }

    @PostMapping("/validateToken")
    public String validate(@RequestBody String requestBody)
    {
        JSONObject jsonObj = (JSONObject) JSONValue.parse(requestBody);
        String token = (String) jsonObj.get("token");

        return String.format("{\"result\":\"%s\"}", (tokenRepository.findTokenByString(token) != null));
    }





}
