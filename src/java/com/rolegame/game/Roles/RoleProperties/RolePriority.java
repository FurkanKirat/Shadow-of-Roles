package com.rolegame.game.Roles.RoleProperties;

public enum RolePriority {
    None(0),
    Low(1),
    Medium(2),
    High(3),
    Extreme(4);

    private final int priority;

    RolePriority(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
