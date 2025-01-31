package com.rolegame.game.models.roles.neutralroles.good;

import com.rolegame.game.models.Player;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

import java.util.ArrayList;
import java.util.List;

public final class Lorekeeper extends NeutralRole implements ActiveNightAbility {
    private final List<Player> alreadyChosenPlayers;
    private Role guessedRole;
    private int trueGuessCount;
    public Lorekeeper() {
        super(RoleID.Lorekeeper, RolePriority.LORE_KEEPER, RoleCategory.NEUTRAL_GOOD, 0, 0);
        trueGuessCount = 0;
        alreadyChosenPlayers = new ArrayList<>();
    }

    @Override
    public boolean performAbility() {
        if(choosenPlayer == null){
            return false;
        }

        if(guessedRole == null){
            return false;
        }
        return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        alreadyChosenPlayers.add(choosenPlayer);

        if(choosenPlayer.getRole().getId() == guessedRole.getId()){
            trueGuessCount++;

            String messageTemplate = LanguageManager.getText("Lorekeeper","abilityMessage");

            String message = messageTemplate
                    .replace("{playerName}", choosenPlayer.getName())
                    .replace("{roleName}", choosenPlayer.getRole().getName());
            sendAbilityAnnouncement(message);
        }
        return false;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(20,1);
    }

    public Role getGuessedRole() {
        return guessedRole;
    }

    public void setGuessedRole(Role guessedRole) {
        this.guessedRole = guessedRole;
    }

    public int getTrueGuessCount() {
        return trueGuessCount;
    }

    public void setTrueGuessCount(int trueGuessCount) {
        this.trueGuessCount = trueGuessCount;
    }

    public List<Player> getAlreadyChosenPlayers() {
        return alreadyChosenPlayers;
    }

    @Override
    public boolean canWinWithOtherTeams() {
        return true;
    }
}
