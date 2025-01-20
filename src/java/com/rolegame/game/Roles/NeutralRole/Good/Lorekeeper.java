package com.rolegame.game.Roles.NeutralRole.Good;

import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Lorekeeper extends NeutralRole implements ActiveNightAbility {
    private Role guessedRole;
    private int trueGuessCount;
    public Lorekeeper() {
        super(RoleID.Lorekeeper, RolePriority.Lorekeeper, RoleCategory.NeutralGood, 0, 1);
        trueGuessCount = 0;
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
        if(choosenPlayer.getRole().getId() == guessedRole.getId()){
            trueGuessCount++;
        }
        return false;
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
}
