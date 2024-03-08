package com.week1.project1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.week1.project1.models.Users;
import com.week1.project1.repositories.UserRepository;

@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository repository) {
		return args -> {
			for(int i=1;i<=5;i++) {
				repository.save(new Users("user "+i));
			}
		};
	}
}
