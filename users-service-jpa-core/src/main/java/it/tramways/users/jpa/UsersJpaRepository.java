package it.tramways.users.jpa;

import it.tramways.users.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersJpaRepository extends JpaRepository<UserEntity, Long> {

}
