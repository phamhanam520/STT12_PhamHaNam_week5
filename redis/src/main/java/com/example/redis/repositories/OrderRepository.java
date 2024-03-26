package com.example.redis.repositories;

import com.example.redis.models.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MyOrder, Long> {
}