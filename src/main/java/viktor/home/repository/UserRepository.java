package viktor.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viktor.home.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

}
