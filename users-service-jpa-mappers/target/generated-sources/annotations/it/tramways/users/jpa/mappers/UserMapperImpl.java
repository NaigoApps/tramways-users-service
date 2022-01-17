package it.tramways.users.jpa.mappers;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;
import it.tramways.users.jpa.RoleEntity;
import it.tramways.users.jpa.UserEntity;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-17T23:37:21+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity map(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setUuid( user.getUuid() );
        userEntity.setUsername( user.getUsername() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setRoles( roleSetToRoleEntitySet( user.getRoles() ) );
        userEntity.setEnabled( user.isEnabled() );

        return userEntity;
    }

    @Override
    public User map(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setUuid( userEntity.getUuid() );
        user.setId( userEntity.getId() );
        user.setUsername( userEntity.getUsername() );
        user.setPassword( userEntity.getPassword() );
        user.setEnabled( userEntity.isEnabled() );
        user.setRoles( roleEntitySetToRoleSet( userEntity.getRoles() ) );

        return user;
    }

    protected RoleEntity roleToRoleEntity(Role role) {
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

    protected Set<RoleEntity> roleSetToRoleEntitySet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleEntity> set1 = new HashSet<RoleEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleEntity( role ) );
        }

        return set1;
    }

    protected Role roleEntityToRole(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Role role;

        switch ( roleEntity ) {
            case ADMIN: role = Role.ADMIN;
            break;
            case EXPERT: role = Role.EXPERT;
            break;
            case CLIENT: role = Role.CLIENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + roleEntity );
        }

        return role;
    }

    protected Set<Role> roleEntitySetToRoleSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRole( roleEntity ) );
        }

        return set1;
    }
}
