package it.tramways.users.api.security;

import it.tramways.security.TramwaysUserDetails;
import it.tramways.users.core.persistable.User;
import it.tramways.users.core.persistable.UsersRepository;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TramwaysUsersDetailsService implements UserDetailsService {

    private final UsersRepository repository;

    public TramwaysUsersDetailsService(
        UsersRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public TramwaysUserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username);
        if (user != null) {
            return convert(user);
        }
        throw new UsernameNotFoundException(username + " not found");
    }

    private TramwaysUserDetails convert(User user) {
        return new TramwaysUserDetails(
            user.getUuid(),
            user.getUsername(),
            user.getPassword(),
            user.listRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(
                Collectors.toSet())
        );
    }
}
