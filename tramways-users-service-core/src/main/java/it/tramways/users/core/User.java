package it.tramways.users.core;

import it.tramways.core.model.DomainEntity;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User extends DomainEntity {

	private String username;

	private boolean enabled;

	private String password;

	private Set<Role> roles;

	public User() {
		roles = EnumSet.noneOf(Role.class);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void assignPassword(String plainPassword, PasswordEncoder encoder) {
		this.password = encoder.encode(plainPassword);
	}

	public boolean passwordMatches(String plainPassword, PasswordEncoder encoder) {
		return encoder.matches(plainPassword, password);
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

	public Set<Role> listRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void assignRoles(Set<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

}
