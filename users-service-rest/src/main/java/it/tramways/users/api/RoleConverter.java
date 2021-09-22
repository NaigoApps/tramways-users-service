package it.tramways.users.api;

import it.tramways.users.api.v1.model.UserRole;
import it.tramways.users.core.Role;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleConverter {

    public static Role toModel(UserRole role) {
        return Role.valueOf(role.name());
    }

    public static Set<Role> toModel(Collection<UserRole> roles) {
        return roles.stream()
            .map(RoleConverter::toModel)
            .collect(Collectors.toSet());
    }

    public static UserRole toDto(Role role) {
        return UserRole.valueOf(role.name());
    }

    public static Set<UserRole> toDto(Collection<Role> roles) {
        return roles.stream()
            .map(RoleConverter::toDto)
            .collect(Collectors.toSet());
    }

}
