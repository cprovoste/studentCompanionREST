package com.studentcompanion.rest.services;

import com.studentcompanion.rest.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService
{
	@Autowired
	UserRepository userRepository;
	TokenRepository tokenRepository;

	public String genHash(String message) throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedHash = digest.digest(message.getBytes(StandardCharsets.UTF_8));



		return bytesToHex(encodedHash);
	}

	private String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();

		//ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae
	}


	 public UserService(UserRepository userRepository, TokenRepository tokenRepository)
	 {
		 this.userRepository = userRepository;
		 this.tokenRepository = tokenRepository;
	 }

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

	public List<Course> findCoursesByUsername(String user)
	{
		return userRepository.findUserByUsername(user).getCourses();
	}

	public User save(User user){

		String encodedPassword = null;
		try {
			encodedPassword = genHash(user.getPassword());
			System.out.println("Password " + encodedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setPassword(encodedPassword);
		 return this.userRepository.save(user);
	}

	public List<Token> getTokens()
	{
		List<User> users = userRepository.findAll();
		List<Token> tokens = new ArrayList<>() {
		};
		for (var u : users) {
			if(u.getToken() != null){
				tokens.add(u.getToken());
			}
		}
		return tokens;
	}

	public User updateUserToken(int userId, String localToken)
	{
		Token token = new Token(localToken);
		User existingUser = userRepository.findById(userId).orElse(null);
		existingUser.setToken(token);
		return userRepository.save(existingUser);
	}



}
