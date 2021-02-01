package it.tramways;

import it.tramways.users.core.Role;
import it.tramways.users.inbound.UsersService;
import it.tramways.users.inbound.commands.CreateUserCommand;
import java.util.Collections;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsersServiceInitializer {

    private final UsersService service;

    public UsersServiceInitializer(
        UsersService service
    ) {
        this.service = service;
    }

    @EventListener
    @Transactional
    public void onApplicationStarted(ContextRefreshedEvent evt) {
        if (service.countByRole(Role.ADMIN) == 0) {
            CreateUserCommand command = new CreateUserCommand();
            command.setUsername("admin");
            command.setPassword("password");
            command.setRoles(Collections.singleton(Role.ADMIN));
            service.create(command);
        }
    }

}
