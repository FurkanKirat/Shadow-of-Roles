package com.rolegame.game.Roles;

import com.rolegame.game.Roles.CorrupterRole.Analyst.DarkRevealer;
import com.rolegame.game.Roles.CorrupterRole.Killing.Psycho;
import com.rolegame.game.Roles.CorrupterRole.Support.Interrupter;
import com.rolegame.game.Roles.FolkRole.Analyst.*;
import com.rolegame.game.Roles.FolkRole.Analyst.Observer;
import com.rolegame.game.Roles.FolkRole.Protector.Soulbinder;
import com.rolegame.game.Roles.FolkRole.Support.SealMaster;
import com.rolegame.game.Roles.NeutralRole.Killing.Assassin;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.Team;

import java.util.*;

public class RoleCatalog {
    private static final HashMap<Team, List<Role>> rolesMap = new HashMap<>();
    private static final HashMap<RoleCategory, List<Role>> categoryMap = new HashMap<>();

    static {
        addRole(new Detective());
        addRole(new Psycho());
        addRole(new Observer());
        addRole(new Soulbinder());
        addRole(new Stalker());
        addRole(new DarkRevealer());
        addRole(new Interrupter());
        addRole(new SealMaster());
        addRole(new Assassin());

    }

    private static void addRole(Role role){
        rolesMap.computeIfAbsent(role.getTeam(), k->new ArrayList<>()).add(role);
        categoryMap.computeIfAbsent(role.getRoleCategory(), k-> new ArrayList<>()).add(role);
    }

    public static List<Role> getRolesByTeam(Team team){
        return rolesMap.getOrDefault(team, Collections.emptyList());
    }

    public static List<Role> getRolesByCategory(RoleCategory roleCategory){

        return categoryMap.getOrDefault(roleCategory, Collections.emptyList());
    }

    public static Role getRandomRole(Role otherRole){
        ArrayList<Role> otherRoles = new ArrayList<>();
        otherRoles.add(new Detective());
        otherRoles.add(new Psycho());
        otherRoles.add(new Observer());
        otherRoles.add(new Soulbinder());
        otherRoles.add(new Stalker());
        otherRoles.add(new DarkRevealer());
        otherRoles.add(new Interrupter());
        otherRoles.add(new SealMaster());
        otherRoles.add(new Assassin());
        otherRoles.remove(otherRole);
        return otherRoles.get(new Random().nextInt(otherRoles.size()));
    }

    public static Role getRandomRole(){
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Detective());
        roles.add(new Psycho());
        roles.add(new Observer());
        roles.add(new Soulbinder());
        roles.add(new Stalker());
        roles.add(new DarkRevealer());
        roles.add(new Interrupter());
        roles.add(new SealMaster());
        roles.add(new Assassin());
        return roles.get(new Random().nextInt(roles.size()));
    }

    public static ArrayList<Role> initializeRoles(int playerCount){
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Assassin());
        roles.add(new Psycho());
        for(int i=2;i<playerCount;i++){
            roles.add(getRandomRole());
        }
        Collections.shuffle(roles);

        return roles;
    }

}
