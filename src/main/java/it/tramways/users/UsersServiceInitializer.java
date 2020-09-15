package it.tramways.users;

import it.tramways.users.persistable.Role;
import it.tramways.users.persistable.User;
import it.tramways.users.persistable.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsersServiceInitializer {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    @Transactional
    public void onApplicationStarted(ContextRefreshedEvent evt){
        if (repository.countUsers(Role.ADMIN) == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEnabled(true);
            admin.assignPassword("password", passwordEncoder);
            admin.grantRole(Role.ADMIN);
            repository.create(admin);
        }
    }

}
