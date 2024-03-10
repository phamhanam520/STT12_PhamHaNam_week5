package iuh.fit.jwt.controllers;

import iuh.fit.jwt.models.MyUser;
import iuh.fit.jwt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class Auth {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public MyUser register(@RequestBody MyUser user) {
        System.out.println(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        MyUser saveUser = userRepository.save(user);
        return saveUser;
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
