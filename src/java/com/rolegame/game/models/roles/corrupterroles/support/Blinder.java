package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;

import java.util.ArrayList;
import java.util.Random;

public final class Blinder extends CorrupterRole{
    public Blinder() {
        super(RoleID.Blinder, AbilityType.OTHER_THAN_CORRUPTER, RolePriority.BLINDER, RoleCategory.CORRUPTER_SUPPORT, 0, 0);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        String message = LanguageManager.getText("Blinder","abilityMessage");
        sendAbilityMessage(message,roleOwner);
        ArrayList<Player> players = new ArrayList<>(GameScreenController.getGameService().getAlivePlayers());

        players.remove(choosenPlayer);

        choosenPlayer.getRole().setChoosenPlayer(players.get(new Random().nextInt(players.size())));

        sendAbilityMessage(LanguageManager.getText("Blinder","blindMessage"), choosenPlayer);

        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(25,10);
    }
}
