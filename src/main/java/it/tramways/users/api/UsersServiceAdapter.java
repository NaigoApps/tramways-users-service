package it.tramways.users.api;

import it.tramways.core.model.TramwaysUserDetails;
import it.tramways.users.RoleConverter;
import it.tramways.users.TokenManager;
import it.tramways.users.TramwaysUsersDetailsService;
import it.tramways.users.api.model.BooleanWrapper;
import it.tramways.users.api.model.ChangePasswordRequest;
import it.tramways.users.api.model.LoginRequest;
import it.tramways.users.api.model.StringWrapper;
import it.tramways.users.api.model.UserRequest;
import it.tramways.users.api.model.UserRole;
import it.tramways.users.persistable.User;
import it.tramways.users.persistable.UsersRepositoryImpl;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceAdapter implements UsersApi {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsersRepositoryImpl repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TramwaysUsersDetailsService usersDetailService;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserConverter converter;

    @Override
    public ResponseEntity<it.tramways.users.api.model.User> createUser(
        @Valid UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.assignPassword(userRequest.getPassword(), encoder);
        user.assignRoles(new HashSet<>(RoleConverter.toModel(userRequest.getRoles())));
        repository.save(user);
        return ResponseEntity.ok(converter.toDto(user));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        repository.deleteByUuid(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> editPassword(String id,
        @Valid ChangePasswordRequest password) {
        return editUser(id, user -> {
            if (user.passwordMatches(password.getOldPassword(), encoder)) {
                user.assignPassword(password.getNewPassword(), encoder);
            } else {
                throw new RuntimeException("Old password not correct");
            }
        });
    }

    @Override
    public ResponseEntity<Void> editRoles(String id, @Valid List<UserRole> userRole) {
        String token = extractToken(null);
        if (isCurrentUser(id, token)) {
            throw new RuntimeException("Cannot edit current user");
        }
        return editUser(id, user -> user.assignRoles(userRole.stream()
            .map(RoleConverter::toModel)
            .collect(Collectors.toSet())));
    }

    private String extractToken(String auth) {
        if (auth == null || !auth.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        return auth.substring(TOKEN_PREFIX.length());
    }

    @Override
    public ResponseEntity<Void> enableUser(String id, @Valid BooleanWrapper booleanWrapper) {
        return null;
    }

    @Override
    public ResponseEntity<it.tramways.users.api.model.User> getUser(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<it.tramways.users.api.model.User>> getUsers() {
        return null;
    }

    @Override
    public ResponseEntity<it.tramways.users.api.model.User> logged() {
        return ResponseEntity.ok(new it.tramways.users.api.model.User());
    }

    @Override
    public ResponseEntity<StringWrapper> login(@Valid LoginRequest loginRequest) {
        authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            ));
        TramwaysUserDetails details = usersDetailService
            .loadUserByUsername(loginRequest.getUsername());
        String token = tokenManager.requestToken(details);
        StringWrapper body = new StringWrapper();
        body.setValue(token);
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .body(body);
    }

    @Override
    public ResponseEntity<Void> resetUser(String id, @Valid StringWrapper stringWrapper) {
        return null;
    }

    private ResponseEntity<Void> editUser(String uuid, Consumer<User> editor) {
        User user = repository.findByUuid(uuid);
        editor.accept(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isCurrentUser(String targetUuid, String token) {
        TramwaysUserDetails user = tokenManager.token2UserDetail(token);
        return user.getUuid().equals(targetUuid);
    }
}
