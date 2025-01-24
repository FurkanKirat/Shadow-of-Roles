package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GUI.Boxes.MessageBox;
import com.rolegame.game.GUI.Boxes.PlayerSelectionBox;
import com.rolegame.game.GUI.Boxes.RoleBox;
import com.rolegame.game.GUI.Boxes.RoleSpecificBoxes.EntrepreneurBox;
import com.rolegame.game.GUI.Boxes.RoleSpecificBoxes.LorekeeperBox;
import com.rolegame.game.GameManagement.*;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.*;
import com.rolegame.game.Roles.FolkRole.Unique.Entrepreneur;
import com.rolegame.game.Roles.NeutralRole.Good.Lorekeeper;
import com.rolegame.game.Roles.RoleProperties.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GameScreenController {

    @FXML
    private TextField abilitiesTextField;

    @FXML
    private ListView<PlayerSelectionBox> alivePlayersListView;

    @FXML
    private ListView<MessageBox> announcementsView;

    @FXML
    private TextField attributesTextField;

    @FXML
    private TextField goalTextField;

    @FXML
    private ListView<String> graveListView;

    @FXML
    private Label roleLabel;

    @FXML
    private TreeView<Object> rolesTreeView;

    @FXML
    private VBox midBox;

    @FXML
    private Label dayLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button useAbilityButton;

    @FXML
    private VBox passTurnPane;

    @FXML
    private ListView<MessageBox> announcementsListView;

    @FXML
    private VBox announceBigVBox;

    @FXML
    private Label announcementsLabel;

    @FXML
    private Label graveyardLabel;

    @FXML
    private Label allRolesLabel;

    @FXML
    private Label goalLabel;

    @FXML
    private Label attributesLabel;

    @FXML
    private Label abilitiesLabel;

    @FXML
    private Label alivePlayersLabel;

    @FXML
    private VBox extraPropertiesVbox;

    @FXML
    private Label passTurnLabel;

    @FXML
    private HBox gameBox;

    private static GameController gameController;

    private static final ArrayList<PlayerSelectionBox> playerSelectionBoxes = new ArrayList<>();

    @FXML
    void useAbilityClicked(ActionEvent event) {

        if(gameController.getCurrentPlayer().getRole().getChoosenPlayer()==null){

            if(gameController.isDay()||(!gameController.isDay() && gameController.getCurrentPlayer().getRole() instanceof ActiveNightAbility)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(LanguageManager.getText("Menu","passAlertTitle"));
                alert.setHeaderText(LanguageManager.getText("Menu","passAlertHead"));
                alert.setContentText(LanguageManager.getText("Menu","passAlertMessage"));

                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() != ButtonType.OK){
                    return;
                }
            }

        }


        passTurnPane.setVisible(true);
        gameController.sendVoteMessages();
        gameController.passTurn();
        if(gameController.getCurrentPlayerIndex()==0){

            toggleDayNightCycle();

        }
        if(gameController.isDay()){
            setStyleImage(passTurnPane,"day");
        }else{
            setStyleImage(passTurnPane,"night");
        }
        changePlayer();
    }

    private static void setStyleImage(Parent root, String time) {
        int randNum = new Random().nextInt(4);
        String style = "-fx-background-image: url(/com/rolegame/game/images/lobby/"+time+randNum+".jpg); " +
                "-fx-background-size: cover;";
        root.getStyleClass().clear();
        root.styleProperty().set(style);
    }


    @FXML
    void startDayClicked(MouseEvent event) {
        announceBigVBox.setVisible(false);
    }

    @FXML
    void interludeClicked(MouseEvent event) {
        passTurnPane.setVisible(false);
    }


    public void initialize(){

        gameController = WriteNamesController.getGameController();
        changePlayer();
        initializeRolesView();
        initializeMessages();
        dayLabel.setText((gameController.isDay() ? LanguageManager.getText("Menu","day"): LanguageManager.getText("Menu","night") ) + ": " +gameController.getDayCount());

        announcementsLabel.setText(LanguageManager.getText("Menu","announcement"));
        allRolesLabel.setText(LanguageManager.getText("Menu","allRoles"));
        graveyardLabel.setText(LanguageManager.getText("Menu","graveyard"));
        goalLabel.setText(LanguageManager.getText("Menu","goal"));
        attributesLabel.setText(LanguageManager.getText("Menu","attributes"));
        abilitiesLabel.setText(LanguageManager.getText("Menu","abilities"));
        alivePlayersLabel.setText(LanguageManager.getText("Menu","alivePlayers"));
        useAbilityButton.setText(LanguageManager.getText("Menu","useAbility"));

        passTurnLabel.setText(LanguageManager.getText("PassTur","turn")
                .replace("{playerName}", gameController.getCurrentPlayer().getName()));
        alivePlayersListView.setSelectionModel(null);
    }

    @FXML
    void selectRole(MouseEvent event) {
        TreeItem<Object> selectedRole = rolesTreeView.getSelectionModel().getSelectedItem();
        if(selectedRole!=null&&selectedRole.getValue() instanceof Role){
            midBox.getChildren().clear();
            midBox.getChildren().add(new RoleBox((Role) selectedRole.getValue()));
        }
    }

    private void initializeRolesView(){
        TreeItem<Object> roles = new TreeItem<>(LanguageManager.getText("Role","role"));
        TreeItem<Object> folk = new TreeItem<>(LanguageManager.getText("Role","folkRole"));
        TreeItem<Object> corrupter = new TreeItem<>(LanguageManager.getText("Role","corrupterRole"));
        TreeItem<Object> neutral = new TreeItem<>(LanguageManager.getText("Role","neutralRole"));

        TreeItem<Object> folkAnalyst = new TreeItem<>(LanguageManager.getText("Role","folkAnalyst"));
        TreeItem<Object> folkProtector = new TreeItem<>(LanguageManager.getText("Role","folkProtector"));
        TreeItem<Object> folkKilling = new TreeItem<>(LanguageManager.getText("Role","folkKilling"));
        TreeItem<Object> folkSupport = new TreeItem<>(LanguageManager.getText("Role","folkSupport"));
        TreeItem<Object> folkUnique = new TreeItem<>(LanguageManager.getText("Role","folkUnique"));

        TreeItem<Object> corrupterAnalyst = new TreeItem<>(LanguageManager.getText("Role","corrupterAnalyst"));
        TreeItem<Object> corrupterKilling = new TreeItem<>(LanguageManager.getText("Role","corrupterKilling"));
        TreeItem<Object> corrupterSupport = new TreeItem<>(LanguageManager.getText("Role","corrupterSupport"));

        TreeItem<Object> neutralEvil = new TreeItem<>(LanguageManager.getText("Role","neutralEvil"));
        TreeItem<Object> neutralKilling = new TreeItem<>(LanguageManager.getText("Role","neutralKilling"));
        TreeItem<Object> neutralChaos = new TreeItem<>(LanguageManager.getText("Role","neutralChaos"));
        TreeItem<Object> neutralGood = new TreeItem<>(LanguageManager.getText("Role","neutralGood"));


        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FOLK_ANALYST)){
            folkAnalyst.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FOLK_PROTECTOR)){
            folkProtector.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FOLK_KILLING)){
            folkKilling.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FOLK_SUPPORT)){
            folkSupport.getChildren().add(new TreeItem<>(role));
        }

        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FOLK_UNIQUE)){
            folkUnique.getChildren().add(new TreeItem<>(role));
        }

        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.CORRUPTER_ANALYST)){
            corrupterAnalyst.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.CORRUPTER_KILLING)){
            corrupterKilling.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.CORRUPTER_SUPPORT)){
            corrupterSupport.getChildren().add(new TreeItem<>(role));
        }

        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.NEUTRAL_EVIL)){
            neutralEvil.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.NEUTRAL_KILLING)){
            neutralKilling.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.NEUTRAL_CHAOS)){
            neutralChaos.getChildren().add(new TreeItem<>(role));
        }

        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.NEUTRAL_GOOD)){
            neutralGood.getChildren().add(new TreeItem<>(role));
        }


        folk.getChildren().addAll(folkAnalyst,folkProtector,folkKilling,folkSupport,folkUnique);
        corrupter.getChildren().addAll(corrupterAnalyst,corrupterKilling,corrupterSupport);
        neutral.getChildren().addAll(neutralEvil,neutralKilling,neutralChaos,neutralGood);
        roles.getChildren().addAll(folk,corrupter,neutral);


        rolesTreeView.setRoot(roles);

    }
    private void changePlayer(){
        nameLabel.setText(gameController.getCurrentPlayer().getName());
        numberLabel.setText(LanguageManager.getText("Menu","number")+": "+gameController.getCurrentPlayer().getNumber());
        abilitiesTextField.setText(gameController.getCurrentPlayer().getRole().getAbilities());
        attributesTextField.setText(gameController.getCurrentPlayer().getRole().getAttributes());
        goalTextField.setText(gameController.getCurrentPlayer().getRole().getGoal());
        roleLabel.setText(gameController.getCurrentPlayer().getRole().getName());

        List<PlayerSelectionBox> boxes = gameController.getAlivePlayers().stream()
                .filter(Player::isAlive)
                .map(player -> new PlayerSelectionBox(player, gameController.getCurrentPlayer(), gameController.isDay()))
                .toList();

        alivePlayersListView.getItems().setAll(boxes);

        playerSelectionBoxes.clear();
        playerSelectionBoxes.addAll(boxes);


        initializeMessages();
        extraPropertiesVbox.getChildren().clear();
        if(gameController.getCurrentPlayer().getRole() instanceof Entrepreneur entrepreneur && !gameController.isDay()){
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.NONE);
            extraPropertiesVbox.getChildren().add(new EntrepreneurBox(entrepreneur));
        }
        else if(gameController.getCurrentPlayer().getRole() instanceof Lorekeeper lorekeeper && !gameController.isDay()){
            extraPropertiesVbox.getChildren().add(new LorekeeperBox(lorekeeper));
        }
        passTurnLabel.setText(LanguageManager.getText("PassTurn","turn")
                .replace("{playerName}", gameController.getCurrentPlayer().getName()));
    }

    private void toggleDayNightCycle(){
        gameController.setDay(!gameController.isDay());
        if(gameController.isDay()){

            useAbilityButton.setText(LanguageManager.getText("Menu","vote"));
            gameController.performAllAbilities();
            dayStartAnnouncements();

            gameBox.getStyleClass().remove("night");
            gameBox.getStyleClass().add("day");
            gameController.setDayCount(getGameController().getDayCount()+1);

        }
        else{

            gameController.executeMaxVoted();
            gameController.updateAlivePlayers();
            dayStartAnnouncements();

            useAbilityButton.setText(LanguageManager.getText("Menu","useAbility"));
            if(!(gameController.getCurrentPlayer() instanceof ActiveNightAbility)){
                useAbilityButton.setText("Pass Turn");
            }
            gameBox.getStyleClass().remove("day");
            gameBox.getStyleClass().add("night");
        }
        gameController.updateAlivePlayers();
        gameController.checkGameFinished();
        graveListView.getItems().clear();

        for(Player deadPlayer: gameController.getDeadPlayers()){
            graveListView.getItems().add(deadPlayer.toString()+" ("+deadPlayer.getRole().getName()+")");
        }
        dayLabel.setText((gameController.isDay() ? LanguageManager.getText("Menu","day") : LanguageManager.getText("Menu","night")) + ": " +gameController.getDayCount());


        initializeMessages();


    }

    private void initializeMessages(){
        announcementsView.getItems().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic() || message.receiver().equals(gameController.getCurrentPlayer())){
                announcementsView.getItems().add(new MessageBox(message,announcementsView));
            }
        }

    }

    private void dayStartAnnouncements(){
        announcementsListView.getItems().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic()&&message.dayCount() == gameController.getDayCount()){
                announcementsListView.getItems().add(new MessageBox(message,announcementsView));
            }
        }
        announceBigVBox.setStyle("-fx-background-image: url(/com/rolegame/game/images/announcements/table.jpg); ");
        announceBigVBox.setVisible(true);
    }


    public static GameController getGameController() {
        return gameController;
    }

    public static ArrayList<PlayerSelectionBox> getPlayerSelectionBoxes() {
        return playerSelectionBoxes;
    }
}
