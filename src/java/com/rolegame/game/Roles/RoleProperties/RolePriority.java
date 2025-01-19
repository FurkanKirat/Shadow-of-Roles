package com.rolegame.game.Roles.RoleProperties;

public enum RolePriority {
    //
    None(0),
    Soulbinder(1),
    FolkHero(2),
    Blinder(3),
    Roleblock(4),
    LastJoke(5);




    private final int priority;

    RolePriority(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
