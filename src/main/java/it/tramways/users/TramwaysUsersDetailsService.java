package it.tramways.users;

import it.tramways.core.model.TramwaysUserDetails;
import it.tramways.users.persistable.User;
import it.tramways.users.persistable.UsersRepository;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TramwaysUsersDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Override
    public TramwaysUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
