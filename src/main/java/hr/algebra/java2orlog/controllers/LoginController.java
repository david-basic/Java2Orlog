package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.PlayerDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

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
    //endregion

    private static PlayerDetails playerOneDetails;
    private static PlayerDetails playerTwoDetails;

    public static PlayerDetails getPlayerOneDetails() {
        return playerOneDetails;
    }
    public static PlayerDetails getPlayerTwoDetails() {
        return playerTwoDetails;
    }

    public void startGame(){
        String playerOneName = tfPlayerOneName.getText();
        String playerTwoName = tfPlayerTwoName.getText();

        lblPlayerOneNameError.setVisible(false);
        lblPlayerTwoNameError.setVisible(false);

        if (playerOneName == "" && playerTwoName == "") {
            lblPlayerOneNameError.setVisible(true);
            lblPlayerTwoNameError.setVisible(true);
            return;
        } else if (playerOneName == ""){
            lblPlayerOneNameError.setVisible(true);
            return;
        } else if (playerTwoName == "") {
            lblPlayerTwoNameError.setVisible(true);
            return;
        }

        playerOneDetails = new PlayerDetails(playerOneName);
        playerTwoDetails = new PlayerDetails(playerTwoName);

        FXMLLoader fxmlLoader = new FXMLLoader(OrlogApplication.class.getResource("gameScreenView.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load(), 1720, 880);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Stage gameScreenStage = OrlogApplication.getMainStage();
        gameScreenStage.setResizable(false);
        gameScreenStage.setTitle("Orlog");
        gameScreenStage.setScene(scene);
        gameScreenStage.show();

        // with this you set the screen dead center in the visual area
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        gameScreenStage.setX((screenBounds.getWidth() - gameScreenStage.getWidth()) / 2);
        gameScreenStage.setY((screenBounds.getHeight() - gameScreenStage.getHeight()) / 2);
    }
}