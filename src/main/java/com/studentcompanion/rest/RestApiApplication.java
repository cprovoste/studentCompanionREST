package com.studentcompanion.rest;

import com.studentcompanion.rest.models.Course;
import com.studentcompanion.rest.models.CourseRepository;
import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserRepository;
import com.studentcompanion.rest.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(UserRepository urepository, CourseRepository crepository)
	{
		return (String[] args) -> {

			Course course = crepository.getReferenceById(0l);

			List<Course> courses = new ArrayList<>();
			courses.add(course);

			Optional<User> ouser = urepository.findById(1);
			if( ouser.isPresent() )
			{
				User user = ouser.get();
				System.out.println("USER : " + user.getUsername());
				user.setCourses(courses);

				urepository.save(user);

			}
			else
			{
				System.out.println("USER NOT FOUND");
			}

		};
	}

}
