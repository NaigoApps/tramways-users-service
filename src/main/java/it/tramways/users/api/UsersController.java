package it.tramways.users.api;

import it.tramways.security.UserRoles;
import it.tramways.users.api.model.BooleanWrapper;
import it.tramways.users.api.model.ChangePasswordRequest;
import it.tramways.users.api.model.LoginRequest;
import it.tramways.users.api.model.StringWrapper;
import it.tramways.users.api.model.User;
import it.tramways.users.api.model.UserRequest;
import it.tramways.users.api.model.UserRole;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tramways/rest")
public class UsersController implements UsersApi {

    @Autowired
    private UsersServiceAdapter service;

    @Override
    public ResponseEntity<User> createUser(@Valid UserRequest userRequest) {
        return service.createUser(userRequest);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return service.deleteUser(id);
    }

    @Override
    public ResponseEntity<Void> editPassword(String id,
        @Valid ChangePasswordRequest changePasswordRequest) {
        return service.editPassword(id, changePasswordRequest);
    }

    @Override
    public ResponseEntity<Void> editRoles(String id, @Valid List<UserRole> userRole) {
        return service.editRoles(id, userRole);
    }

    @Override
    public ResponseEntity<Void> enableUser(String id, @Valid BooleanWrapper booleanWrapper) {
        return service.enableUser(id, booleanWrapper);
    }

    @Override
    public ResponseEntity<User> getUser(String id) {
        return service.getUser(id);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return service.getUsers();
    }

    @Override
    @Secured(UserRoles.ADMIN)
    public ResponseEntity<User> logged() {
        return service.logged();
    }

    @Override
    public ResponseEntity<StringWrapper> login(@Valid LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    @Override
    public ResponseEntity<Void> resetUser(String id, @Valid StringWrapper stringWrapper) {
        return service.resetUser(id, stringWrapper);
    }
}
