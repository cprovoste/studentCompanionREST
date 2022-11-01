package com.studentcompanion.rest;

import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(UserRepository repository)
	{
		return (String[] args) -> {
			Optional<User> ouser = repository.findById(1);
			if( ouser.isPresent() )
			{
				User user = ouser.get();
				System.out.println("USER : " + user.getUsername());

			}
			else
			{
				System.out.println("USER NOT FOUND");
			}

		};
	}

}
