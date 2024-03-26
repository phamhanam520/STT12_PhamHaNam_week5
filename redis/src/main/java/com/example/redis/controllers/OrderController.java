package com.example.redis.controllers;

import com.example.redis.models.MyOrder;
import com.example.redis.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RedisTemplate redisTemplate;
    RestTemplate restTemplate = new RestTemplate();

    // use redisTemplate to save order
    @PostMapping("/order")
    public void saveOrder() {
         MyOrder order = new MyOrder();
         order.setTotalPrice(100.0);
         orderRepository.save(order);
         // use reidsTemplate to save order
        redisTemplate.opsForList().leftPush("orders", order);
    }
    @GetMapping("/getAll")
    public ResponseEntity getAll(@RequestHeader("token") String token){
        // use restemplate to authenticate token
        if (token == null || token.isEmpty())
            return ResponseEntity.badRequest().body("Token is invalid");
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "http://localhost:8081/auth/checkToken";
        HttpMethod getMethod = HttpMethod.GET;
        ResponseEntity<Boolean> response = restTemplate.exchange(url, getMethod, entity, Boolean.class);
        if (!response.getBody())
            return ResponseEntity.badRequest().body("Token is invalid");
        // use redis to find order with price > 100
        List<MyOrder> orders = redisTemplate.opsForList().range("orders", 0, -1);
        return  ResponseEntity.ok(orders);
    }
}
