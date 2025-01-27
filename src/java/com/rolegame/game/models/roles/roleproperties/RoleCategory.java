package com.rolegame.game.models.roles.roleproperties;

public enum RoleCategory {
    FOLK_ANALYST(0),
    FOLK_PROTECTOR(1),
    FOLK_KILLING(2),
    FOLK_SUPPORT(3),
    FOLK_UNIQUE(4),

    CORRUPTER_ANALYST(5),
    CORRUPTER_KILLING(6),
    CORRUPTER_SUPPORT(7),

    NEUTRAL_EVIL(8),
    NEUTRAL_CHAOS(9),
    NEUTRAL_KILLING(10),
    NEUTRAL_GOOD(11);

    private final int category;


    RoleCategory(int category){
        this.category = category;
    }
    public int getCategory(){
        return category;
    }

}
