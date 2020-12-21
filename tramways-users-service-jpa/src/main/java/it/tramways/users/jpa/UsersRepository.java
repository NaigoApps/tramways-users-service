package it.tramways.users.jpa;

import it.tramways.users.core.User;
import it.tramways.users.outbound.UsersServiceRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long>, UsersServiceRepository {

}
