package com.rolegame.game.Roles.RoleProperties;

import com.rolegame.game.Roles.NeutralRole.Chaos.SimplePerson;
import com.rolegame.game.Roles.NeutralRole.Killing.Assassin;

public enum RoleID {
    Mold(-1),

    Detective(1),
    Observer(2),
    SoulBinder(3),
    Stalker(4),
    Psycho(5),
    DarkRevealer(6),
    Interrupter(7),
    SealMaster(8),
    Assassin(9),
    SimplePerson(10);
    RoleID(int i) {
    }
}
