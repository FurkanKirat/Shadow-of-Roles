package com.rolegame.game.gui.controllers.gameguide;

import com.rolegame.game.gui.boxes.RoleBoxForGameGuide;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.RoleCatalog;
import com.rolegame.game.models.roles.roleproperties.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RolesController {

    @FXML
    private VBox rolesVBox;

    @FXML
    private HBox buttonsHBox;

    @FXML
    void corruptClicked(MouseEvent event) {
        displayRolesByTeamAndCategory(filterRolesByCategory(Team.CORRUPTER));
    }

    @FXML
    void folkClicked(MouseEvent event) {
        displayRolesByTeamAndCategory(filterRolesByCategory(Team.FOLK));
    }

    @FXML
    void neutralClicked(MouseEvent event){
        displayRolesByTeamAndCategory(filterRolesByCategory(Team.NEUTRAL));
    }

    /**
     * Initializes the roles and adds RoleBox elements to the VBox.
     */
    public void initialize() {
        // Display all roles grouped by team and category
        displayRolesByTeamAndCategory(filterRolesByCategory(Team.FOLK));
    }
    private ArrayList<Role> filterRolesByCategory(Team team){
        ArrayList<Role> roles = new ArrayList<>(RoleCatalog.getRolesByTeam(team));
        bubbleSort(roles);
        return roles;

    }

    private void bubbleSort(ArrayList<Role> roles){
        for(int i = 0;i<roles.size();i++){
            for(int k = 0;k<roles.size()-i-1;k++){
                if(roles.get(k).getRoleCategory().getCategory() > roles.get(k+1).getRoleCategory().getCategory()){
                    Role temp = roles.get(k);
                    roles.set(k,roles.get(k+1));
                    roles.set(k+1,temp);

                }
            }
        }
    }
    private void displayRolesByTeamAndCategory(ArrayList<Role> roles) {
        rolesVBox.getChildren().clear();
        for(int i = 0;i<roles.size();i++){
            if(i == 0 || roles.get(i).getRoleCategory() != roles.get(i-1).getRoleCategory()){
                Label categoryLabel = new Label("Category: " + roles.get(i).getRoleCategory());
                categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");
                rolesVBox.getChildren().add(categoryLabel);
            }
            RoleBoxForGameGuide roleBox = new RoleBoxForGameGuide(roles.get(i));
            rolesVBox.getChildren().add(roleBox);
        }
    }


}
