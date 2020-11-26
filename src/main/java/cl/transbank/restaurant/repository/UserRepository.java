package cl.transbank.restaurant.repository;

import cl.transbank.restaurant.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameEquals(String username);
}
