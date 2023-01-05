package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.GameState;
import hr.algebra.java2orlog.models.PlayerDetails;
import hr.algebra.java2orlog.models.PlayerMetaData;
import hr.algebra.java2orlog.server.Server;
import hr.algebra.java2orlog.thread.ClientThread;
import hr.algebra.java2orlog.utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginController implements Initializable {
    //region FXML elements
    @FXML
    private TextField tfPlayerName;
    @FXML
    private Label lblPlayerName;
    @FXML
    private Label lblPlayerNameError;
    @FXML
    private RadioButton rbFirst;
    @FXML
    private RadioButton rbSecond;
    //endregion

    private static PlayerDetails playerDetails;
    private static List<PlayerDetails> playerDetailsCollection = new ArrayList<>();
    private static Map<Long, PlayerMetaData> playersMetaData = new HashMap<>();

    public static PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public static List<PlayerDetails> getPlayerDetailsCollection() {
        return playerDetailsCollection;
    }

    public static Map<Long, PlayerMetaData> getPlayersMetaData() {
        return playersMetaData;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup radioButtonGroup = new ToggleGroup();
        rbFirst.setToggleGroup(radioButtonGroup);
        rbSecond.setToggleGroup(radioButtonGroup);

        rbFirst.setSelected(true);

        //lblPlayerName.setText(OrlogApplication.getPlayerName());
    }

    @FXML
    public void startGame() {

        String playerName = tfPlayerName.getText();

        lblPlayerNameError.setVisible(false);

        if (playerName.equals("")) {
            lblPlayerNameError.setVisible(true);
            return;
        }

        System.out.println("Client thread is about to get started!");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new ClientThread(new GameState()));
        System.out.println("Client thread started!");

        try (Socket clientSocket = new Socket(Server.HOST, Server.PORT)) {

            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

            System.err.println("Client is connecting to " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

            System.out.println("Connecting to address: " + clientSocket.getLocalAddress().toString().substring(1));

            PlayerMetaData newPlayerMetaData = new PlayerMetaData(
                    clientSocket.getLocalAddress().toString().substring(1),
                    String.valueOf(clientSocket.getPort()),
                    playerName,
                    ProcessHandle.current().pid(),
                    rbFirst.isSelected()
            );

            playersMetaData.put(ProcessHandle.current().pid(), newPlayerMetaData);

            oos.writeObject(newPlayerMetaData);

            System.out.println("Object metadata sent to server!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        playerDetails = new PlayerDetails(playerName, "0", "0", "0");

        playerDetailsCollection.add(playerDetails);

        try {
            FxmlUtils.showScreen("gameScreenView.fxml", OrlogApplication.getMainStage(), 1920, 880, "Orlog");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void openResultsView() {

        //region Testing data for table, comment out if not used
//        playerDetails = new PlayerDetails("John Doe1", "1", "2", "3");
//        playerDetailsCollection.add(playerDetails);
//        playerDetails = new PlayerDetails("John Doe2", "3", "2", "1");
//        playerDetailsCollection.add(playerDetails);
        //endregion

        try {
            FxmlUtils.showScreen("resultsView.fxml", OrlogApplication.getMainStage(), 600, 400, "Results");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}