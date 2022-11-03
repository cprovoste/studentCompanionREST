package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u from User u WHERE u.username = ?1")
	User findUserByUsername(String username);


}
