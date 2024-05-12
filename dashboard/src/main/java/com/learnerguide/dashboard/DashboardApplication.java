package com.learnerguide.dashboard;

import com.learnerguide.dashboard.entities.User;
import com.learnerguide.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

}

@Component
class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public InitialDataLoader(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Check if a user with the same email already exists
		String email = "admin@example.com"; // Specify the email for the new user
		User existingUser = userRepository.findByEmail(email);

		// If the user doesn't exist, create and save a new user
		if (existingUser == null) {
			User newUser = new User(email, passwordEncoder.encode("1234"), "ADMIN", "John Doe");
			userRepository.save(newUser);
			System.out.println("New user created and saved to the database.");
		} else {
			System.out.println("User with email " + email + " already exists. Skipping creation.");
		}
	}
}
