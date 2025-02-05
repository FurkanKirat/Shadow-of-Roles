package com.rolegame.game.models.roles;

import com.rolegame.game.models.player.Player;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;
import com.rolegame.game.models.roles.enums.Team;
import com.rolegame.game.services.MessageService;

import java.util.Objects;

public abstract class Role {
    protected final RoleID id;
    protected RolePriority rolePriority;
    protected final RoleCategory roleCategory;
    protected final Team team;
    protected Player roleOwner;
    protected Player choosenPlayer;
    protected double attack;
    protected double defence;
    protected boolean canPerform;

    public Role(RoleID id, RolePriority rolePriority, RoleCategory roleCategory,
                Team team, double attack ,double defence
    ) {
        // IMPORTANT! When adding a new role, the role id and role name in the lang json files must be the same!
        this.id = id;
        this.rolePriority = rolePriority;
        this.roleCategory = roleCategory;
        this.team = team;
        this.attack = attack;
        this.defence = defence;
        this.canPerform = true;
    }

    public Role(Role role) {
        this.id = role.id;
        this.rolePriority = role.rolePriority;
        this.roleCategory = role.roleCategory;
        this.team = role.team;
        this.attack = role.getAttack();
        this.defence = role.getDefence();
        this.canPerform = true;

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
            sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockedMessage"),roleOwner);
            return false;
        }

        if(choosenPlayer==null){
            return false;
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,roleOwner);
            return false;
        }

        return executeAbility();
    }

    protected boolean performAbilityForPassiveRoles(){
        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockedMessage"),getRoleOwner());
            return false;
        }
        return executeAbility();
    }

    protected boolean performAbilityForBlockImmuneRoles(){
        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage") ,getRoleOwner());
        }

        if(getChoosenPlayer()==null){
            return false;
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,getRoleOwner());
            return false;
        }
        return executeAbility();
    }

    protected void sendAbilityMessage(String message, Player receiver){
        MessageService.sendNightMessage(message, receiver, false, false);
    }

    protected void sendAbilityAnnouncement(String message){
        MessageService.sendNightMessage(message, null, true, false);
    }

    public abstract boolean executeAbility();

    public abstract boolean isRoleBlockImmune();

    @Override
    public String toString() {
        return getName();
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
    // Getter and Setters

    public final RoleID getId() {
        return id;
    }
    
    public final String getName() {
        return LanguageManager.getRoleText(id.toString(),"name");
    }

    public final String getAttributes() {
        return LanguageManager.getText(id.toString(),"attributes");
    }

    public final String getAbilities() {
        return LanguageManager.getText(id.toString(),"abilities");
    }

    public abstract String getGoal();

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

    public abstract ChanceProperty getChanceProperty();

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
