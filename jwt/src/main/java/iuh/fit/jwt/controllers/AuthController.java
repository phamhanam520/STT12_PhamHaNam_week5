package iuh.fit.jwt.controllers;

import iuh.fit.jwt.models.MyUser;
import iuh.fit.jwt.models.UserPrincial;
import iuh.fit.jwt.services.AuthService;
import iuh.fit.jwt.services.UserService;
import iuh.fit.jwt.utils.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public MyUser register(@RequestBody MyUser user) {
        System.out.println(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        MyUser saveUser = authService.register(user);
        return saveUser;
    }

    @GetMapping("/login")
    public String login(@RequestBody MyUser user) {
        MyUser myUser = userService.getMyUserFromUsername(user.getUsername());
        if (myUser == null)
            return "User not found";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(user.getPassword(), myUser.getPassword()))
            return "Wrong password";

        UserPrincial userPrincial = new UserPrincial(myUser);
        String token = Jwt.generateToken(userPrincial);
        return token;
    }

    @GetMapping("/checkToken")
    public boolean checkToken(@RequestHeader(name = "token") String token) {
        return !Jwt.isTokenExpired(token);
    }
    @GetMapping("/hello")
    public String hello(@RequestHeader(name = "token") String token) {
        if (Jwt.isTokenExpired(token))
            return "Token expired";
        return "hello";
    }
}
