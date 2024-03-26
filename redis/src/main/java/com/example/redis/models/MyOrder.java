package com.example.redis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter @Setter
@ToString
public class MyOrder implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private double totalPrice;
}
