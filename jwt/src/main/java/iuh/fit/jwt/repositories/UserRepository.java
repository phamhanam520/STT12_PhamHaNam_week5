package iuh.fit.jwt.repositories;

import iuh.fit.jwt.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
}
