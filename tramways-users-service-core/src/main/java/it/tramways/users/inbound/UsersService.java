package it.tramways.users.inbound;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;
import it.tramways.users.inbound.commands.ChangePasswordCommand;
import it.tramways.users.inbound.commands.ChangeRolesCommand;
import it.tramways.users.inbound.commands.CreateUserCommand;
import it.tramways.users.inbound.commands.LoginCommand;
import it.tramways.users.inbound.commands.UserCommand;
import java.util.List;

public interface UsersService {

    User create(CreateUserCommand command);

    void delete(String id);

    void editPassword(ChangePasswordCommand command);

    void editRoles(ChangeRolesCommand command);

    void enable(UserCommand request);

    void disable(UserCommand request);

    User findByUsername(String username);

    User findByUuid(String uuid);

    List<User> findAll();

    Long countByRole(Role role);

    String login(LoginCommand command);

    void reset(UserCommand command);
}
