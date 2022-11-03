package com.studentcompanion.rest.services;

import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserService
{
	@Autowired
	UserRepository repository;

	@GetMapping("/users")
	public List<User> all()
	{
		return repository.findAll();
	}

	@GetMapping("/get-by-username/{username}")
	public User getUserByUsername(@PathVariable String username)
	{
		return repository.findUserByUsername(username);
	}


}
