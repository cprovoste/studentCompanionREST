package com.studentcompanion.rest.services;

import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserService
{
	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<User> all()
	{
		return userRepository.findAll();
	}

	@GetMapping(value = "/{user}", produces = "application/json")
	public User getUser(@PathVariable String user) {
		return getUserByUsername(user);
	}

	@GetMapping("/get-by-username/{username}")
	public User getUserByUsername(@PathVariable String username){return userRepository.findUserByUsername(username);}


}
