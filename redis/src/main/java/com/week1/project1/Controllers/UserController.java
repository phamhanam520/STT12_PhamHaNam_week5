package com.week1.project1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.week1.project1.models.Users;
import com.week1.project1.repositories.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository repository;
	@GetMapping("/users")
	ResponseEntity getUsers(){
		return new ResponseEntity(repository.findAll(),HttpStatus.OK);
	}
	@GetMapping("/user/{id}")
	Users getUserById(@PathVariable(name = "id") long id) {
		return repository.findById(id).get();
	}

	@PostMapping("/user")
	ResponseEntity postNewUser(@RequestBody Users user){
		repository.save(user);
		return new 	ResponseEntity(user,HttpStatus.OK);
	};
}
