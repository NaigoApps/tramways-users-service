package it.tramways.users.jpa.mappers;

import it.tramways.users.core.User;
import it.tramways.users.jpa.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity map(User user);

    User map(UserEntity userEntity);

}
