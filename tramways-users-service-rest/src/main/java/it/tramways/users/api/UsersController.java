package it.tramways.users.api;

import it.tramways.commons.web.model.wrappers.StringWrapper;
import it.tramways.users.api.model.ChangePasswordRequest;
import it.tramways.users.api.model.LoginRequest;
import it.tramways.users.api.model.User;
import it.tramways.users.api.model.UserRequest;
import it.tramways.users.api.model.UserRole;
import it.tramways.users.api.security.TramwaysUserDetailsService;
import it.tramways.users.inbound.UsersService;
import it.tramways.users.inbound.commands.ChangePasswordCommand;
import it.tramways.users.inbound.commands.ChangeRolesCommand;
import it.tramways.users.inbound.commands.CreateUserCommand;
import it.tramways.users.inbound.commands.UserCommand;
import it.tramways.commons.web.security.TokenManager;
import it.tramways.commons.web.security.TramwaysUserDetails;
import it.tramways.commons.web.UserRoles;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tramways/rest")
public class UsersController implements UsersApi {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final UsersService service;

    private final AuthenticationManager authenticationManager;

    private final TramwaysUserDetailsService usersDetailService;

    private final TokenManager tokenManager;

    private final UserConverter converter;

    public UsersController(
        UsersService service,
        AuthenticationManager authenticationManager,
        TramwaysUserDetailsService usersDetailService,
        TokenManager tokenManager,
        UserConverter converter
    ) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.usersDetailService = usersDetailService;
        this.tokenManager = tokenManager;
        this.converter = converter;
    }

    @Override
    public ResponseEntity<User> createUser(@Valid UserRequest userRequest) {
        CreateUserCommand command = new CreateUserCommand();
        command.setUsername(userRequest.getUsername());
        command.setPassword(userRequest.getPassword());
        command.setRoles(new HashSet<>(RoleConverter.toModel(userRequest.getRoles())));
        it.tramways.users.core.User user = service.create(command);
        return ResponseEntity.ok(converter.toDto(user));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> editPassword(String id,
        @Valid ChangePasswordRequest changePasswordRequest) {
        ChangePasswordCommand command = new ChangePasswordCommand();
        command.setUsername(id);
        command.setOldPassword(changePasswordRequest.getOldPassword());
        command.setNewPassword(changePasswordRequest.getNewPassword());
        service.editPassword(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> editRoles(String id, @Valid List<UserRole> userRole) {
        ChangeRolesCommand command = new ChangeRolesCommand();
        command.setUsername(id);
        command
            .setNewRoles(userRole.stream().map(RoleConverter::toModel).collect(Collectors.toSet()));
        service.editRoles(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String id) {
        return ResponseEntity.ok(converter.toDto(service.findByUsername(id)));
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.findAll().stream()
            .map(converter::toDto)
            .collect(Collectors.toList()));
    }

    @Override
    @Secured(UserRoles.ADMIN)
    public ResponseEntity<User> logged() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            it.tramways.users.core.User loggedUser = service.findByUsername(username);
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
    public ResponseEntity<Void> resetUser(String id, @Valid Object body) {
        UserCommand command = new UserCommand();
        command.setUsername(id);
        service.reset(command);
        return ResponseEntity.ok().build();
    }

}
