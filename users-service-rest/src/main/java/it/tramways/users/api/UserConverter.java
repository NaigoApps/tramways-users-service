package it.tramways.users.api;

import it.tramways.users.api.v1.model.User;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public User toDto(it.tramways.users.core.User user) {
        User result = new User();
        result.setUuid(user.getUuid());
        result.setUsername(user.getUsername());
        result.setRoles(new ArrayList<>(RoleConverter.toDto(user.getRoles())));
        return result;
    }

}
