package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u from User u WHERE u.username = ?1")
	User findUserByUsername(String username);

	@Query(value = "SELECT * from User u WHERE u.token_id = ?1", nativeQuery = true)
	List<User> findUserByToken(String token_id);
}
