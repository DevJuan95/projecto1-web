package unir.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unir.com.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
