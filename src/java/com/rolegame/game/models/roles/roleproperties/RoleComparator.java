package com.rolegame.game.models.roles.roleproperties;

import com.rolegame.game.models.roles.Role;

import java.util.Comparator;

public class RoleComparator implements Comparator<Role> {
    @Override
    public int compare(Role role1, Role role2) {
        if(role1.getRolePriority().getPriority() < role2.getRolePriority().getPriority()){
            return 1;
        }
        else if(role1.getRolePriority().getPriority() > role2.getRolePriority().getPriority()){
            return -1;
        }
        return 0;
    }
}
