package com.studentcompanion.rest.services;

import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserDTO;
import com.studentcompanion.rest.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService
{
	@Autowired
	UserRepository userRepository;

	public List<UserDTO> getAllUsers()
	{
		return userRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	public User findUserByUsername(String user) {
		return userRepository.findUserByUsername(user);
	}

	private UserDTO convertEntityToDTO(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());

		return userDTO;
	}




}
