package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.PlayerDetails;
import hr.algebra.java2orlog.server.Server;
import hr.algebra.java2orlog.utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    //region FXML elements
    @FXML
    private TextField tfPlayerName;
    @FXML
    private Label lblPlayerName;
    @FXML
    private Label lblPlayerNameError;
    @FXML
    private Label lblPlayerOrder;
    //endregion

    private static PlayerDetails playerDetails;
    private static List<PlayerDetails> playerDetailsCollection = new ArrayList<>();

    public static PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public static List<PlayerDetails> getPlayerDetailsCollection() {
        return playerDetailsCollection;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblPlayerName.setText(OrlogApplication.getPlayerName());
        lblPlayerOrder.setText(OrlogApplication.getPlayerOrderChoice());
    }

    @FXML
    public void startGame() {

        try (Socket clientSocket = new Socket(Server.HOST, Server.PORT)) {
            System.err.println("Client connecting to: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

//            sendSerializableRequest(clientSocket);

        } catch (IOException e) { //| ClassNotFoundException e) {
            e.printStackTrace();
        }


        String playerName = tfPlayerName.getText();

        lblPlayerNameError.setVisible(false);

        if (playerName.equals("")) {
            lblPlayerNameError.setVisible(true);
            return;
        }

        playerDetails = new PlayerDetails(playerName, "0", "0", "0");

        playerDetailsCollection.add(playerDetails);

        try {
            FxmlUtils.showScreen("gameScreenView.fxml", OrlogApplication.getMainStage(), 1720, 880, "Orlog");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void openResultsView() {

        //region Testing data for table, comment out if not used
        playerDetails = new PlayerDetails("John Doe1", "1", "2", "3");
        playerDetailsCollection.add(playerDetails);
        playerDetails = new PlayerDetails("John Doe2", "3", "2", "1");
        playerDetailsCollection.add(playerDetails);
        //endregion

        try {
            FxmlUtils.showScreen("resultsView.fxml", OrlogApplication.getMainStage(), 600, 400, "Results");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}