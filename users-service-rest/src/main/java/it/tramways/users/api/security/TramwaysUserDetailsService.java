package it.tramways.users.api.security;

import it.tramways.users.inbound.UsersService;
import it.tramways.commons.web.security.TramwaysUserDetails;
import it.tramways.users.core.User;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TramwaysUserDetailsService implements UserDetailsService {

    private final UsersService service;

    public TramwaysUserDetailsService(
        UsersService service
    ) {
        this.service = service;
    }

    @Override
    public TramwaysUserDetails loadUserByUsername(String username) {
        User user = service.findByUsername(username);
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
            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(
                Collectors.toSet())
        );
    }
}
