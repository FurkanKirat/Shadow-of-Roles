package com.rolegame.game.models.roles.templates;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.Message;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.services.MessageService;

import java.util.ArrayList;
import java.util.Objects;

public abstract class RoleTemplate {

    protected final RoleID id;
    protected final RoleCategory roleCategory;
    protected final Team team;
    protected RolePriority rolePriority;
    protected double attack;
    protected double defence;
    protected boolean isRoleBlockImmune;
    protected AbilityType abilityType;

    public RoleTemplate(RoleID id, AbilityType abilityType, RolePriority rolePriority, RoleCategory roleCategory,
                        Team team, double attack ,double defence) {
        // IMPORTANT! When adding a new role template, the role id and role name in the lang json files must be the same!
        this.id = id;
        this.abilityType = abilityType;
        this.rolePriority = rolePriority;
        this.roleCategory = roleCategory;
        this.team = team;
        this.attack = attack;
        this.defence = defence;
    }

    @Override
    public final boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleTemplate that = (RoleTemplate) o;
        return id == that.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public final String toString(){
        return getName();
    }
    public final RoleTemplate copy() {
        try {
            return this.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create copy of Role", e);
        }
    }

    public abstract AbilityResult executeAbility(Player roleOwner, Player choosenPlayer);
    public AbilityResult performAbility(Player roleOwner, Player choosenPlayer){
        if(!roleOwner.getRole().isCanPerform()&&!roleOwner.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockedMessage"),roleOwner);
            return AbilityResult.ROLE_BLOCKED;
        }

        if(choosenPlayer==null){
            return AbilityResult.NO_ONE_SELECTED;
        }

        if(choosenPlayer.isImmune()){
            return AbilityResult.TARGET_IMMUNE;
        }

        return executeAbility(roleOwner, choosenPlayer);
    }

    protected final AbilityResult performAbilityForPassiveRoles(Player roleOwner){
        if(!roleOwner.getRole().isCanPerform()&&!roleOwner.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockedMessage"),roleOwner);
            return AbilityResult.ROLE_BLOCKED;
        }
        return executeAbility(roleOwner, null);
    }

    protected final AbilityResult performAbilityForBlockImmuneRoles(Player roleOwner, Player choosenPlayer){

        if(roleOwner.getRole().getChoosenPlayer()==null){
            return AbilityResult.NO_ONE_SELECTED;
        }

        if(choosenPlayer.isImmune()){
            return AbilityResult.TARGET_IMMUNE;
        }
        return executeAbility(roleOwner, choosenPlayer);
    }

    protected final void sendAbilityMessage(String message, Player receiver){
        MessageService.createNightMessage(message, receiver, false, false);
    }

    protected final void sendAbilityAnnouncement(String message){
        MessageService.createNightMessage(message, null, true, false);
    }

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

    public final RoleCategory getRoleCategory() {
        return roleCategory;
    }

    public final void setRolePriority(RolePriority rolePriority) {
        this.rolePriority = rolePriority;
    }

    public final boolean isRoleBlockImmune() {
        return isRoleBlockImmune;
    }

    public final void setRoleBlockImmune(boolean roleBlockImmune) {
        isRoleBlockImmune = roleBlockImmune;
    }

    public final AbilityType getAbilityType() {
        return abilityType;
    }

    public final void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
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
