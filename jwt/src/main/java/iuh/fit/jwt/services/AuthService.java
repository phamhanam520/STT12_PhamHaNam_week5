package iuh.fit.jwt.services;

import iuh.fit.jwt.models.MyUser;
import iuh.fit.jwt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public MyUser register(MyUser user) {
        return userRepository.save(user);
    }
}
