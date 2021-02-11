package it.tramways.users.jpa.mappers;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;
import it.tramways.users.jpa.RoleEntity;
import it.tramways.users.jpa.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleEntity map(Role role);

    Role map(RoleEntity role);

}
