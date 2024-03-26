package com.example.redis;

import com.example.redis.models.MyOrder;
import com.example.redis.repositories.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Autowired
    private RedisTemplate redisTemplate;
//     init data
     @Bean
     CommandLineRunner init(OrderRepository orderRepository) {
         return args -> {

             MyOrder order1 = new MyOrder();
             order1.setTotalPrice(100.0);
             orderRepository.save(order1);


             MyOrder order2 = new MyOrder();
             order2.setTotalPrice(200.0);
             orderRepository.save(order2);


             MyOrder order3 = new MyOrder();
             order3.setTotalPrice(200.0);
             orderRepository.save(order3);

             List<MyOrder> orders = new ArrayList<>();
                orders.add(order1);
                orders.add(order2);
                orders.add(order3);
                // clear all items
                redisTemplate.delete("orders");
                // use reidsTemplate to save order
                redisTemplate.opsForList().leftPushAll("orders", orders);
                // show all orders
                List<MyOrder> orders1 = redisTemplate.opsForList().range("orders", 0, -1);
                orders1.forEach(System.out::println);

         };
    };
}
