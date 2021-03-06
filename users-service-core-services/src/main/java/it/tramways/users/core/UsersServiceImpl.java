package it.tramways.users.core;

import it.tramways.users.inbound.UsersService;
import it.tramways.users.inbound.commands.ChangePasswordCommand;
import it.tramways.users.inbound.commands.ChangeRolesCommand;
import it.tramways.users.inbound.commands.CreateUserCommand;
import it.tramways.users.inbound.commands.LoginCommand;
import it.tramways.users.inbound.commands.UserCommand;
import it.tramways.users.outbound.UsersRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(CreateUserCommand command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.assignPlainPassword(command.getPassword(), passwordEncoder);
        user.setRoles(command.getRoles());
        return repository.createUser(user);
    }

    @Override
    public void delete(String uuid) {
        repository.deleteByUuid(uuid);
    }

    @Override
    public void editPassword(ChangePasswordCommand command) {
        User target = repository.findByUuid(command.getUuid());
        if (target == null) {
            throw new RuntimeException("Cannot find user");
        }
        if (!target.passwordMatches(command.getOldPassword(), passwordEncoder)) {
            throw new RuntimeException("Wrong password");
        }
        String encodedPassword = passwordEncoder.encode(command.getNewPassword());
        repository.editPassword(command.getUuid(), encodedPassword);
    }

    @Override
    public void editRoles(ChangeRolesCommand command) {
        repository.editRoles(command.getUuid(), command.getNewRoles());
    }

    @Override
    public void enable(UserCommand command) {
        repository.editEnabling(command.getUuid(), true);
    }

    @Override
    public void disable(UserCommand command) {
        repository.editEnabling(command.getUuid(), false);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User findByUuid(String uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Long countByRole(Role role) {
        return repository.countByRole(role);
    }

    @Override
    public String login(LoginCommand command) {
        return null;
    }

    @Override
    public void reset(UserCommand command) {
        repository.editPassword(command.getUuid(), command.getUuid());
    }
}
