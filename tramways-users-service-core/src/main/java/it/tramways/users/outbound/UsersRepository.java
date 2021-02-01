package it.tramways.users.outbound;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;
import java.util.List;
import java.util.Set;

public interface UsersRepository {

    User createUser(User user);

    void deleteByUuid(String uuid);

    void editPassword(String username, String newPassword);

    void editRoles(String username, Set<Role> newRoles);

    void editEnabling(String username, boolean b);

    User findByUsername(String username);

    User findByUuid(String uuid);

    List<User> findAll();

    Long countByRole(Role role);
}
