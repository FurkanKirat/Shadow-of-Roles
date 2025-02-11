package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;

public final class LastJoke extends CorrupterRole {
    private boolean didUsedAbility;
    public LastJoke() {
        super(RoleID.LastJoke, AbilityType.OTHER_THAN_CORRUPTER,
                RolePriority.LAST_JOKE, RoleCategory.CORRUPTER_SUPPORT, 3, 0);
        this.didUsedAbility = false;
    }

    @Override
    public AbilityResult performAbility(Player roleOwner, Player choosenPlayer) {
        return executeAbility(roleOwner,choosenPlayer);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        if(!didUsedAbility && !roleOwner.isAlive()){
            didUsedAbility = true;

            if(choosenPlayer==null){
                return AbilityResult.NO_ONE_SELECTED;
            }
            choosenPlayer.setAlive(false);
            choosenPlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath","lastJoke"));
            sendAbilityAnnouncement(LanguageManager.getText("LastJoke","slainMessage")
                    .replace("{playerName}", choosenPlayer.getName()));

            return AbilityResult.SUCCESSFUL;
        }

        return AbilityResult.NO_ABILITY_USE_LEFT;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(15,1);
    }

    public boolean isDidUsedAbility() {
        return didUsedAbility;
    }
}


