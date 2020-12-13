package it.tramways.users.core.persistable;

public interface UsersRepositoryCustom {

    User findByUsername(String user);

    Long countUsers(Role role);

    void deleteByUuid(String id);

    User findByUuid(String value);
}
