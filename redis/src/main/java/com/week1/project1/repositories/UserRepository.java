package com.week1.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.week1.project1.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
}
