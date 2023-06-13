package com.studentcompanion.rest;

import com.studentcompanion.rest.models.*;
import com.studentcompanion.rest.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(UserRepository urepository, CourseRepository crepository, UserService userService, TokenRepository tokenRepository)
	{
		return (String... args) -> {


			Optional<User> ouser = urepository.findById(13);
			if( ouser.isPresent() )
			{
				User user = ouser.get();
				System.out.println("USER : " + user.getUsername());

			}
			else
			{
				System.out.println("USER NOT FOUND");
			}

			if (args.length > 0) {
				if (args[0].equals("--save")) {
					for (User  user : urepository.findAll()) {
						System.out.println("Attempting save!");
						userService.save(user);
					}
				}
			}



		};
	}

}
