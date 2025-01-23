package com.rolegame.game.Roles;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

import java.util.Objects;

public abstract class Role {
    protected final RoleID id;
    protected RolePriority rolePriority;
    protected final RoleCategory roleCategory;
    protected final String name;
    protected final String attributes;
    protected final String abilities;
    protected final Team team;
    protected final String goal;
    protected Player roleOwner;
    protected Player choosenPlayer;
    protected double attack;
    protected double defence;
    protected boolean canPerform;
    protected final ChanceProperty chanceProperty;

    public Role(RoleID id, RolePriority rolePriority, RoleCategory roleCategory,
                Team team, String goal,
                double attack ,double defence, ChanceProperty chanceProperty
    ) {
        // IMPORTANT! When adding a new role, the role id and role name in the lang json files must be the same!
        this.id = id;
        this.rolePriority = rolePriority;
        this.roleCategory = roleCategory;
        this.name = LanguageManager.getRoleText(id.toString()+".name");
        this.attributes = LanguageManager.getText(id +".attributes");
        this.team = team;
        this.abilities = LanguageManager.getText(id +".abilities");
        this.goal = goal;
        this.attack = attack;
        this.defence = defence;
        this.canPerform = true;
        this.chanceProperty = chanceProperty;
    }

    public Role(Role role) {
        this.id = role.id;
        this.rolePriority = role.rolePriority;
        this.roleCategory = role.roleCategory;
        this.name = role.name;
        this.attributes = role.attributes;
        this.team = role.team;
        this.abilities = role.abilities;
        this.goal = role.goal;
        this.attack = role.getAttack();
        this.defence = role.getDefence();
        this.canPerform = true;
        this.chanceProperty = role.chanceProperty;

        this.roleOwner = role.roleOwner;
        this.choosenPlayer = roleOwner.getRole().getChoosenPlayer();
    }

    public final Role copy() {
        try {
            return this.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create copy of Role", e);
        }
    }

    public boolean performAbility(){
        if(!canPerform){
            sendAbilityMessage(LanguageManager.getText("RoleBlock.roleBlockedMessage"),roleOwner,false);
            return false;
        }

        if(choosenPlayer==null){
            return false;
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock.immuneMessage") ,roleOwner,false);
            return false;
        }

        return executeAbility();
    }

    protected void sendAbilityMessage(String message, Player receiver, boolean isPublic){
        Message.sendMessage(message, receiver, isPublic, false);
    }

    public abstract boolean executeAbility();

    @Override
    public String toString(){
        return name;
    }

    // Getter and Setters

    public final RoleID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public final String getName() {
        return name;
    }

    public final String getAttributes() {
        return attributes;
    }

    public final String getAbilities() {
        return abilities;
    }

    public final String getGoal() {
        return goal;
    }

    public final RolePriority getRolePriority() {
        return rolePriority;
    }

    public final Team getTeam() {
        return team;
    }

    public final Player getChoosenPlayer() {
        return choosenPlayer;
    }

    public final void setChoosenPlayer(Player choosenPlayer) {
        this.choosenPlayer = choosenPlayer;
    }

    public final double getAttack() {
        return attack;
    }

    public final void setAttack(double attack) {
        this.attack = attack;
    }

    public final double getDefence() {
        return defence;
    }

    public final void setDefence(double defence) {
        this.defence = defence;
    }

    public final Player getRoleOwner() {
        return roleOwner;
    }

    public final void setRoleOwner(Player roleOwner) {
        this.roleOwner = roleOwner;
    }

    public final boolean isCanPerform() {
        return canPerform;
    }

    public final void setCanPerform(boolean canPerform) {
        this.canPerform = canPerform;
    }

    public final RoleCategory getRoleCategory() {
        return roleCategory;
    }

    public final void setRolePriority(RolePriority rolePriority) {
        this.rolePriority = rolePriority;
    }

    public ChanceProperty getChanceProperty() {
        return chanceProperty;
    }

    public record ChanceProperty(int chance, int maxNumber) {
        public ChanceProperty {
            if (chance <= 0) {
                throw new IllegalArgumentException("Chance must be positive");
            }
            if (maxNumber <= 0) {
                throw new IllegalArgumentException("Max number must be positive");
            }
        }
    }
}
