package it.tramways.users.api;

import it.tramways.users.api.model.User;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public User toDto(it.tramways.users.core.User user){
        User result = new User();
        result.setUuid(user.getUuid());
        result.setUsername(user.getUsername());
        result.setRoles(new ArrayList<>(RoleConverter.toDto(user.listRoles())));
        result.setResourceType(User.class.getSimpleName());
        return result;
    }

}
