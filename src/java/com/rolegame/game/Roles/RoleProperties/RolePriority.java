package com.rolegame.game.Roles.RoleProperties;

public enum RolePriority {

    NONE(0),
    SOULBINDER(1),
    FOLK_HERO(2),
    BLINDER(3),
    ROLE_BLOCK(4),
    LORE_KEEPER(5),
    LAST_JOKE(6);




    private final int priority;

    RolePriority(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
