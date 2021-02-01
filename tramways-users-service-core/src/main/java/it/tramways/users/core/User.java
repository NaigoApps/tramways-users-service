package it.tramways.users.core;

import it.tramways.core.DomainEntity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User extends DomainEntity {

    private String username;

    private Boolean enabled;

    private String rawPassword;

    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return rawPassword;
    }

    public void assignPlainPassword(String plainPassword, PasswordEncoder encoder) {
        this.rawPassword = encoder.encode(plainPassword);
    }

    public void setPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public boolean passwordMatches(String plainPassword, PasswordEncoder encoder) {
        return encoder.matches(plainPassword, rawPassword);
    }

    public boolean hasRole(Role r) {
        return roles.contains(r);
    }

    public void grantRole(Role r) {
        roles.add(r);
    }

    public void revokeRole(Role r) {
        roles.remove(r);
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = new HashSet<>(roles);
    }

}
