package it.tramways.users.outbound;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;

public interface UsersServiceRepository {

    User findByUsername(String user);

    Long countUsers(Role role);

    void deleteByUuid(String id);

    User findByUuid(String value);
}
