package it.tramways.users.api;

import it.tramways.commons.api.model.BooleanWrapper;
import it.tramways.commons.api.model.StringWrapper;
import it.tramways.web.security.TokenManager;
import it.tramways.web.security.TramwaysUserDetails;
import it.tramways.users.api.model.ChangePasswordRequest;
import it.tramways.users.api.model.LoginRequest;
import it.tramways.users.api.model.UserRequest;
import it.tramways.users.api.model.UserRole;
import it.tramways.users.api.security.TramwaysUsersDetailsService;
import it.tramways.users.core.User;
import it.tramways.users.outbound.UsersServiceRepository;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceAdapter implements UsersApi {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final PasswordEncoder encoder;

    private final UsersServiceRepository repository;

    private final AuthenticationManager authenticationManager;

    private final TramwaysUsersDetailsService usersDetailService;

    private final TokenManager tokenManager;

    private final UserConverter converter;

    public UsersServiceAdapter(
        PasswordEncoder encoder,
        UsersServiceRepository repository,
        AuthenticationManager authenticationManager,
        TramwaysUsersDetailsService usersDetailService,
        TokenManager tokenManager,
        UserConverter converter
    ) {
        this.encoder = encoder;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.usersDetailService = usersDetailService;
        this.tokenManager = tokenManager;
        this.converter = converter;
    }

    @Override
    public ResponseEntity<it.tramways.users.api.model.User> createUser(
        @Valid UserRequest userRequest
    ) {
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (isCurrentUser(id, username)) {
            throw new RuntimeException("Cannot edit current user");
        }
        return editUser(id, user -> user.assignRoles(userRole.stream()
            .map(RoleConverter::toModel)
            .collect(Collectors.toSet())));
    }

    @Override
    public ResponseEntity<Void> enableUser(String id, @Valid BooleanWrapper enabled) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (isCurrentUser(id, username)) {
            throw new RuntimeException("Cannot edit current user");
        }
        return editUser(id, user -> user.setEnabled(enabled.getValue()));
    }

    @Override
    public ResponseEntity<it.tramways.users.api.model.User> getUser(String id) {
        return ResponseEntity.ok(converter.toDto(repository.findByUsername(id)));
    }

    @Override
    public ResponseEntity<List<it.tramways.users.api.model.User>> getUsers() {
        return ResponseEntity.ok(repository.findAll().stream()
            .map(converter::toDto)
            .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<it.tramways.users.api.model.User> logged() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            User loggedUser = repository.findByUsername(username);
            return ResponseEntity.ok(converter.toDto(loggedUser));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
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
            .header(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token)
            .body(body);
    }

    @Override
    public ResponseEntity<Void> resetUser(String id, @Valid StringWrapper userId) {
        User target = repository.findByUuid(userId.getValue());
        target.assignPassword(target.getUsername(), new BCryptPasswordEncoder());
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Void> editUser(String uuid, Consumer<User> editor) {
        User user = repository.findByUuid(uuid);
        editor.accept(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isCurrentUser(String targetUuid, String loggedUsername) {
        return targetUuid.equals(repository.findByUsername(loggedUsername).getUuid());
    }
}
