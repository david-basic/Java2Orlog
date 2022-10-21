package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.PlayerDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    //region Fields
    private Popup popup = new Popup();
    private Stage stage = OrlogApplication.getMainStage();
    private ObservableList<PlayerDetails> playerCollection = FXCollections.observableArrayList(new ArrayList<>(LoginController.getPlayerDetailsCollection()));
    //endregion

    //region FXML elements
    @FXML
    private TableView<PlayerDetails> resultsTable;
    @FXML
    private TableColumn<PlayerDetails, String> playerName;
    @FXML
    private TableColumn<PlayerDetails, Integer> wins;
    @FXML
    private TableColumn<PlayerDetails, Integer> draws;
    @FXML
    private TableColumn<PlayerDetails, Integer> loses;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerName.setCellValueFactory(new PropertyValueFactory<PlayerDetails, String>("playerName"));
        wins.setCellValueFactory(new PropertyValueFactory<PlayerDetails, Integer>("numberOfWins"));
        draws.setCellValueFactory(new PropertyValueFactory<PlayerDetails, Integer>("numberOfDraws"));
        loses.setCellValueFactory(new PropertyValueFactory<PlayerDetails, Integer>("numberOfLoses"));
        resultsTable.setItems(playerCollection);
        resultsTable.setVisible(true);

        gameRulesPopupSetup();
    }

    private void gameRulesPopupSetup() {
        ImageView imgRules = new ImageView(Objects.requireNonNull(getClass().getResource("/gameRules.jpg")).toExternalForm());
        popup.setHeight(386);
        popup.setWidth(681);
        popup.getContent().add(imgRules);
        imgRules.setFitWidth(681);
        imgRules.setFitHeight(386);
        popup.setAutoHide(true);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        popup.setX((screenBounds.getWidth() - popup.getWidth()));
        popup.setY((screenBounds.getHeight() - popup.getHeight()) / 2);
    }

    @FXML
    private void showRulesImage() {
        if (!popup.isShowing()) {
            popup.show(stage);
        }
    }

    @FXML
    private void returnToLoginView() {
        FXMLLoader fxmlLoader = new FXMLLoader(OrlogApplication.class.getResource("loginView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 858, 438);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Stage loginScreenStage = OrlogApplication.getMainStage();
        loginScreenStage.setResizable(false);
        loginScreenStage.setTitle("Login");
        loginScreenStage.setScene(scene);
        loginScreenStage.show();

        // with this you set the screen dead center in the visual area
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        loginScreenStage.setX((screenBounds.getWidth() - loginScreenStage.getWidth()) / 2);
        loginScreenStage.setY((screenBounds.getHeight() - loginScreenStage.getHeight()) / 2);
    }

    @FXML
    private void openMovesPlayerView() {
        FXMLLoader fxmlLoader = new FXMLLoader(OrlogApplication.class.getResource("playerMovesView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Stage playerMovesScreenStage = OrlogApplication.getMainStage();
        playerMovesScreenStage.setResizable(false);
        playerMovesScreenStage.setTitle("Player moves");
        playerMovesScreenStage.setScene(scene);
        playerMovesScreenStage.show();

        // with this you set the screen dead center in the visual area
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        playerMovesScreenStage.setX((screenBounds.getWidth() - playerMovesScreenStage.getWidth()) / 2);
        playerMovesScreenStage.setY((screenBounds.getHeight() - playerMovesScreenStage.getHeight()) / 2);
    }

}
