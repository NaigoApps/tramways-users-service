package it.tramways.users.inbound.commands;

import it.tramways.users.core.Role;
import java.util.Set;

public class ChangeRolesCommand extends UserCommand {

    private Set<Role> newRoles;

    public Set<Role> getNewRoles() {
        return newRoles;
    }

    public void setNewRoles(Set<Role> newRoles) {
        this.newRoles = newRoles;
    }
}
