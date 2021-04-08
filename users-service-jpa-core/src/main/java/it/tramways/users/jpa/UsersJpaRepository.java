package it.tramways.users.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersJpaRepository extends JpaRepository<UserEntity, Long> {

}
