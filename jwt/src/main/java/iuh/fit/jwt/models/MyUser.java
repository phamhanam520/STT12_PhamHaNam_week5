package iuh.fit.jwt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter @Setter @ToString
public class MyUser {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String role;

    public MyUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
