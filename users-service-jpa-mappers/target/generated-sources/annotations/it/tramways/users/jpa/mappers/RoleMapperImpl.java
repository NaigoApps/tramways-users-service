package it.tramways.users.jpa.mappers;

import it.tramways.users.core.Role;
import it.tramways.users.jpa.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-16T09:24:37+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleEntity map(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity;

        switch ( role ) {
            case ADMIN: roleEntity = RoleEntity.ADMIN;
            break;
            case EXPERT: roleEntity = RoleEntity.EXPERT;
            break;
            case CLIENT: roleEntity = RoleEntity.CLIENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + role );
        }

        return roleEntity;
    }

    @Override
    public Role map(RoleEntity role) {
        if ( role == null ) {
            return null;
        }

        Role role1;

        switch ( role ) {
            case ADMIN: role1 = Role.ADMIN;
            break;
            case EXPERT: role1 = Role.EXPERT;
            break;
            case CLIENT: role1 = Role.CLIENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + role );
        }

        return role1;
    }
}
