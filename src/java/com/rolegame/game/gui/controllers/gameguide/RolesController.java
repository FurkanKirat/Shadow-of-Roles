package com.rolegame.game.gui.controllers.gameguide;

import com.rolegame.game.gui.components.boxes.RoleBoxForGameGuide;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.templates.RoleTemplate;
import com.rolegame.game.services.RoleService;
import com.rolegame.game.models.roles.enums.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;

public class RolesController {

    @FXML
    private VBox rolesVBox;

    @FXML
    private Button corruptButton;

    @FXML
    private Button folkButton;

    @FXML
    private Button neutralButton;

    @FXML
    private VBox largeVBox;

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
        corruptButton.setText(LanguageManager.getText("Role","CORRUPTER"));
        folkButton.setText(LanguageManager.getText("Role","FOLK"));
        neutralButton.setText(LanguageManager.getText("Role","NEUTRAL"));

        // Display all roles grouped by team and category
        displayRolesByTeamAndCategory(filterRolesByCategory(Team.FOLK));

        VBox.setVgrow(largeVBox, Priority.ALWAYS);
    }
    private ArrayList<RoleTemplate> filterRolesByCategory(Team team){
        ArrayList<RoleTemplate> roles = new ArrayList<>(RoleService.getRolesByTeam(team));
        roles.sort(Comparator.comparing(roleTemplate -> roleTemplate.getRoleCategory().getId()));
        return roles;

    }

    private void displayRolesByTeamAndCategory(ArrayList<RoleTemplate> roles) {
        rolesVBox.getChildren().clear();
        for(int i = 0;i<roles.size();i++){
            if(i == 0 || roles.get(i).getRoleCategory() != roles.get(i-1).getRoleCategory()){
                Label categoryLabel = new Label(
                        LanguageManager.getText("Role",LanguageManager.enumToJsonKey(roles.get(i).getRoleCategory().name())));
                categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");
                rolesVBox.getChildren().add(categoryLabel);
            }
            RoleBoxForGameGuide roleBox = new RoleBoxForGameGuide(roles.get(i));
            rolesVBox.getChildren().add(roleBox);
        }
    }


}
