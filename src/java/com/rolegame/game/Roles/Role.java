package com.rolegame.game.Roles;

import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

import java.util.Objects;

public abstract class Role {
    private final RoleID id;
    private final RolePriority rolePriority;
    private final RoleCategory roleCategory;
    private final String name;
    private final String attributes;
    private final String abilities;
    private final Team team;
    private final String goal;
    private Player roleOwner;
    private Player choosenPlayer;
    private double attack;
    private double defence;
    private boolean canPerform;


    public Role(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, String name,
                String attributes, Team team, String abilities, String goal,
                double attack ,double defence
    ) {
        this.id = id;
        this.rolePriority = rolePriority;
        this.roleCategory = roleCategory;
        this.name = name;
        this.attributes = attributes;
        this.team = team;
        this.abilities = abilities;
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
