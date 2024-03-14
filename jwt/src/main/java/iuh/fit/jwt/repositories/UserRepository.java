package iuh.fit.jwt.repositories;

import iuh.fit.jwt.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    @Query("select u from MyUser u where u.username = ?1")
    MyUser findByUsername(String username);
}
