package com.iuh.jwt.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.jwt.models.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    // Add custom query methods here if needed
}