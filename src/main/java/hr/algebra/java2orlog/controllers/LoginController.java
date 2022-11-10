package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.PlayerDetails;
import hr.algebra.java2orlog.utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginController {
    //region FXML elements
    @FXML
    private TextField tfPlayerOneName;

    @FXML
    private TextField tfPlayerTwoName;

    @FXML
    private Label lblPlayerOneNameError;

    @FXML
    private Label lblPlayerTwoNameError;

    @FXML
    private Label lblSameNamesError;
    //endregion

    private static PlayerDetails playerOneDetails;
    private static PlayerDetails playerTwoDetails;
    private static List<PlayerDetails> playerDetailsCollection = new ArrayList<>();

    public static PlayerDetails getPlayerOneDetails() {
        return playerOneDetails;
    }
    public static PlayerDetails getPlayerTwoDetails() {
        return playerTwoDetails;
    }
    public static List<PlayerDetails> getPlayerDetailsCollection() {
//        return new ArrayList<>(playerDetailsCollection);
        return playerDetailsCollection;
    }

    @FXML
    public void startGame(){
        String playerOneName = tfPlayerOneName.getText();
        String playerTwoName = tfPlayerTwoName.getText();

        lblPlayerOneNameError.setVisible(false);
        lblPlayerTwoNameError.setVisible(false);
        lblSameNamesError.setVisible(false);

        if (playerOneName.equals("") && playerTwoName.equals("")) {
            lblPlayerOneNameError.setVisible(true);
            lblPlayerTwoNameError.setVisible(true);
            return;
        } else if (playerOneName.equals("")){
            lblPlayerOneNameError.setVisible(true);
            return;
        } else if (Objects.equals(playerTwoName, "")) {
            lblPlayerTwoNameError.setVisible(true);
            return;
        }

        if (playerOneName.equals(playerTwoName)){
            lblSameNamesError.setVisible(true);
            return;
        }

        playerOneDetails = new PlayerDetails(playerOneName, "0", "0" , "0");
        playerTwoDetails = new PlayerDetails(playerTwoName, "0", "0", "0");

        playerDetailsCollection.add(playerOneDetails);
        playerDetailsCollection.add(playerTwoDetails);

        try {
            FxmlUtils.showScreen("gameScreenView.fxml", OrlogApplication.getMainStage(), 1720,880, "Orlog");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void openResultsView(){

        //region Testing data for table, comment out if not used
//        playerOneDetails = new PlayerDetails("John Doe1", "1", "2", "3");
//        playerTwoDetails = new PlayerDetails("John Doe2", "3", "2", "1");
//
//        playerDetailsCollection.add(playerOneDetails);
//        playerDetailsCollection.add(playerTwoDetails);
        //endregion

        try {
            FxmlUtils.showScreen("resultsView.fxml", OrlogApplication.getMainStage(), 600,400, "Results");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}