package com.rolegame.game.models.roles.neutralroles.chaos;

import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;

public final class Clown extends NeutralRole{
    public Clown() {
        super(RoleID.Clown, AbilityType.NO_ABILITY, RolePriority.NONE, RoleCategory.NEUTRAL_CHAOS, 0, 0);
    }

    @Override
    public AbilityResult performAbility(Player roleOwner, Player choosenPlayer) {
        return AbilityResult.NO_ABILITY_EXIST;
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        return AbilityResult.NO_ABILITY_EXIST;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,1);
    }

    @Override
    public boolean canWinWithOtherTeams() {
        return true;
    }
}
