package com.rolegame.game.Roles;

import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

import java.util.Objects;

public abstract class Role {
    protected final RoleID id;
    protected final RolePriority rolePriority;
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


    public Role(RoleID id, RolePriority rolePriority, RoleCategory roleCategory,
                Team team, String goal,
                double attack ,double defence
    ) {
        // IMPORTANT! When adding a new role, the role name and role id in the lang json files must be the same!
        this.id = id;
        this.rolePriority = rolePriority;
        this.roleCategory = roleCategory;
        this.name = LanguageManager.getText(id.toString()+".name");
        this.attributes = LanguageManager.getText(id +".attributes");
        this.team = team;
        this.abilities = LanguageManager.getText(id +".abilities");;
        this.goal = goal;
        this.attack = attack;
        this.defence = defence;
        this.canPerform = true;
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
        this.roleOwner = role.roleOwner;
        this.choosenPlayer = roleOwner.getRole().getChoosenPlayer();
    }

    public abstract boolean performAbility();

    @Override
    public String toString(){
        return name;
    }

    // Getter and Setters

    public RoleID getId() {
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

    public String getName() {
        return name;
    }

    public String getAttributes() {
        return attributes;
    }

    public String getAbilities() {
        return abilities;
    }

    public String getGoal() {
        return goal;
    }

    public RolePriority getRolePriority() {
        return rolePriority;
    }

    public Team getTeam() {
        return team;
    }

    public Player getChoosenPlayer() {
        return choosenPlayer;
    }

    public void setChoosenPlayer(Player choosenPlayer) {
        this.choosenPlayer = choosenPlayer;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public Player getRoleOwner() {
        return roleOwner;
    }

    public void setRoleOwner(Player roleOwner) {
        this.roleOwner = roleOwner;
    }

    public boolean isCanPerform() {
        return canPerform;
    }

    public void setCanPerform(boolean canPerform) {
        this.canPerform = canPerform;
    }

    public RoleCategory getRoleCategory() {
        return roleCategory;
    }
}
