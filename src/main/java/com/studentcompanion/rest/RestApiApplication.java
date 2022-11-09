package com.studentcompanion.rest;

import com.studentcompanion.rest.models.Course;
import com.studentcompanion.rest.models.CourseRepository;
import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserRepository;
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
	public CommandLineRunner run(UserRepository urepository, CourseRepository crepository, UserService userService)
	{
		return (String... args) -> {

			Course course = crepository.getReferenceById(0l);
			Course course2 = crepository.getReferenceById(1l);
			Course course3 = crepository.getReferenceById(2l);
			Course course4 = crepository.getReferenceById(3l);

			List<Course> courses = new ArrayList<>();
			courses.add(course);
			courses.add(course2);
			courses.add(course3);
			courses.add(course4);

			Optional<User> ouser = urepository.findById(11);
			if( ouser.isPresent() )
			{
				User user = ouser.get();
				System.out.println("USER : " + user.getUsername());
				user.setCourses(courses);
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
