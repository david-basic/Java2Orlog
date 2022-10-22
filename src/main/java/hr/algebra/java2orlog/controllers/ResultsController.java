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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    //region Fields
    private Popup popup = new Popup();
    private Stage stage = OrlogApplication.getMainStage();


//    private ObservableList<PlayerDetails> playerCollection = FXCollections.observableArrayList(new ArrayList<>(LoginController.getPlayerDetailsCollection()));
    private ObservableList<PlayerDetails> playerCollection;
    //endregion

    //region FXML elements
    @FXML
    private TableView<PlayerDetails> resultsTable;
    @FXML
    private TableColumn<PlayerDetails, String> playerName;
    @FXML
    private TableColumn<PlayerDetails, String> numberOfWins;
    @FXML
    private TableColumn<PlayerDetails, String> numberOfDraws;
    @FXML
    private TableColumn<PlayerDetails, String> numberOfLost;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        playerName.setCellValueFactory(new PropertyValueFactory<PlayerDetails, String>("playerName"));
        numberOfWins.setCellValueFactory(new PropertyValueFactory<PlayerDetails, String>("numberOfWins"));
        numberOfDraws.setCellValueFactory(new PropertyValueFactory<PlayerDetails, String>("numberOfDraws"));
        numberOfLost.setCellValueFactory(new PropertyValueFactory<PlayerDetails, String>("numberOfLost"));

        playerCollection = FXCollections.observableArrayList(LoginController.getPlayerDetailsCollection());

        resultsTable.setItems(playerCollection);

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

        if (LoginController.getPlayerDetailsCollection().size() != 0) {
            FXMLLoader fxmlLoader = new FXMLLoader(OrlogApplication.class.getResource("playerMovesView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1200, 800);
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
        } else {
            Alert canNotAccessPlayerMoves = new Alert(Alert.AlertType.INFORMATION);
            canNotAccessPlayerMoves.setTitle("Access denied");
            canNotAccessPlayerMoves.setHeaderText("No match played this session!");
            canNotAccessPlayerMoves.setContentText("Can not access player moves page because there is nothing to show.");
            canNotAccessPlayerMoves.showAndWait();
        }

    }

}
