package it.tramways.users.persistable;

public interface UsersRepository {
    User create(User u);
    User findByUsername(String user);
    Long countUsers(Role role);
}
