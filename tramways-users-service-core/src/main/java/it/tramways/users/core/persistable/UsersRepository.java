package it.tramways.users.core.persistable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long>, UsersRepositoryCustom{

}
