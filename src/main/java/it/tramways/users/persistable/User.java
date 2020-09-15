package it.tramways.users.persistable;

import it.tramways.core.model.persistable.BaseEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private String username;

	private boolean enabled;

	private String password;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
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

	private static MessageDigest getAlgorithm() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Algorithm not found");
		}
	}

	public void assignRoles(Set<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

}
