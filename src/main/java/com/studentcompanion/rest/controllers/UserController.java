package com.studentcompanion.rest.controllers;

import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserDTO;
import com.studentcompanion.rest.models.UserRepository;
import com.studentcompanion.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List <UserDTO> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/get-by-username/{username}")
    public User getUserByUsername(@PathVariable String username){return userService.findUserByUsername(username);}



}
