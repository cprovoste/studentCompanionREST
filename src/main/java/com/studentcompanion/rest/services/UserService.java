package com.studentcompanion.rest.services;

import com.studentcompanion.rest.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService
{
	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenRepository tokenRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	CourseCommentRepository courseCommentRepository;
	@Autowired
	ProfessorCommentRepository professorCommentRepository;
	@Autowired
	ProfessorRepository professorRepository;

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

	public List<Course> getAllCourses()
	{
		return courseRepository.findAll();
	}

	public List<Professor> getAllProfessors()
	{
		return professorRepository.findAll();
	}

	public User findUserByUsername(String user) {
		return userRepository.findUserByUsername(user);
	}

	public User findUserByToken(String token_id)
	{
		List<User> users =  userRepository.findAll();
		for (User user : users) {
			if(user.getToken() != null) {
				System.out.println("savedToken: "+ user.getToken().getToken());
				System.out.println("TokenID: "+token_id);
				if (user.getToken().getToken().equals(token_id)) {
					return user;
				}
			}
		}
		return null;
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

	public Course findCourseByName(String name)
	{
		return courseRepository.findCourseByName(name);
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

	public CourseComment addComment(String comment, int rating, String localToken, String course){

		CourseComment c = new CourseComment();
		c.setComment(comment);
		c.setRating(rating);


		User existingUser = findUserByToken(localToken);

		List<CourseComment> comments = existingUser.getComments();
		c.setUser(existingUser);

		Course existingCourse = findCourseByName(course);
		c.setCourse(existingCourse);
		comments.add(c);

		existingUser.setComments(comments);

		return courseCommentRepository.save(c);
	}

	public ProfessorComment addProfessorComment(String comment, String professor,int rating, String localToken){

		ProfessorComment c = new ProfessorComment();
		c.setComment(comment);
		Professor actualProfessor = professorRepository.findProfessorByName(professor);
		c.setProfessor(actualProfessor);
		c.setRating(rating);

		User existingUser = findUserByToken(localToken);

		List<ProfessorComment> comments = existingUser.getProfessorComments();
		c.setUser(existingUser);
		comments.add(c);

		existingUser.setProfessorComments(comments);

		return professorCommentRepository.save(c);
	}


	public CourseComment updateCourseRating(int commentID, int courseScore, boolean voted) {

		CourseComment c = courseCommentRepository.getReferenceById(commentID);
		c.setRating(courseScore);
		c.setVoted(voted);

		return courseCommentRepository.save(c);
	}

	public ProfessorComment updateProfessorRating(int commentID, int professorScore, boolean voted) {

		ProfessorComment c = professorCommentRepository.getReferenceById(commentID);
		c.setRating(professorScore);
		c.setVoted(voted);

		return professorCommentRepository.save(c);
	}


	public Boolean getCommentVotedBoolean (int commentID)
	{
		CourseComment c = courseCommentRepository.getReferenceById(commentID);

		return c.isVoted();
	}

	public Boolean getProfessorCommentVotedBoolean (int commentID)
	{
		ProfessorComment c = professorCommentRepository.getReferenceById(commentID);

		return c.isVoted();
	}

}
