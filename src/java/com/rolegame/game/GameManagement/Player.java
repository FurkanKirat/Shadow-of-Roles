package com.rolegame.game.GameManagement;

import com.rolegame.game.Roles.Role;

import java.util.Objects;

public class Player {
    private final int number;
    private final String name;
    private Role role;
    private boolean isAlive;
    private double attack;
    private double defence;
    private boolean hasWon;
    private String causeOfDeath;

    public Player(int number, String name, Role role) {
        this.number = number;
        this.name = name;
        this.role = role;
        this.isAlive = true;
        this.attack = role.getAttack();
        this.defence = role.getDefence();
        this.role.setRoleOwner(this);
        hasWon = false;
        causeOfDeath = null;
    }

    @Override
    public String toString(){
        return number +". " +name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return number == player.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }
}
