package it.tramways.users.api;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;
import it.tramways.users.outbound.UsersServiceRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsersServiceInitializer {

    private final UsersServiceRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UsersServiceInitializer(
        UsersServiceRepository repository,
        PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    @Transactional
    public void onApplicationStarted(ContextRefreshedEvent evt) {
        if (repository.countUsers(Role.ADMIN) == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEnabled(true);
            admin.assignPassword("password", passwordEncoder);
            admin.grantRole(Role.ADMIN);
            repository.save(admin);
        }
    }

}
