package com.rolegame.game.models.player;

import com.rolegame.game.models.roles.Role;

import java.util.Objects;

public abstract class Player {
    private final int number;
    private final String name;
    private Role role;
    private boolean isAlive;
    private double attack;
    private double defence;
    private boolean hasWon;
    private String causeOfDeath;
    private boolean isImmune;
    private boolean isRevealed;

    public Player(int number, String name, Role role) {
        this.number = number;
        this.name = name;
        this.role = role;
        this.isAlive = true;
        this.role.setRoleOwner(this);
        this.isRevealed = false;
        hasWon = false;
        causeOfDeath = null;
    }

    public final void resetStates(){
        this.getRole().setChoosenPlayer(null);
        this.setDefence(this.getRole().getTemplate().getDefence());
        this.setAttack(this.getRole().getTemplate().getAttack());
        this.getRole().setCanPerform(true);
        this.setImmune(false);
    }

    @Override
    public final String toString(){
        return number +". " +name;
    }

    @Override
    public final boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return number == player.number;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(number);
    }

    public final int getNumber() {
        return number;
    }

    public final String getName() {
        return name;
    }

    public final Role getRole() {
        return role;
    }

    public final void setRole(Role role) {
        this.role = role;
        this.role.setRoleOwner(this);
    }

    public final boolean isAlive() {
        return isAlive;
    }

    public final void setAlive(boolean alive) {
        isAlive = alive;
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

    public final boolean isHasWon() {
        return hasWon;
    }

    public final void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public final String getCauseOfDeath() {
        return causeOfDeath;
    }
    public final void setImmune(boolean isImmune){
        this.isImmune = isImmune;
    }

    public final boolean isImmune(){
        return isImmune;
    }

    public final void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public final boolean isRevealed() {
        return isRevealed;
    }

    public final void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public abstract boolean isAIPlayer();
}
