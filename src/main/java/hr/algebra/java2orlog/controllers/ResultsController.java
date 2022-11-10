package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.PlayerDetails;
import hr.algebra.java2orlog.utils.FxmlUtils;
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
    public void showRulesImage() {
        if (!popup.isShowing()) {
            popup.show(stage);
        }
    }

    @FXML
    public void returnToLoginView() {
        try {
            FxmlUtils.showScreen("loginView.fxml", OrlogApplication.getMainStage(), 858,438, "Login");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void openMovesPlayerView() {
        if (LoginController.getPlayerDetailsCollection().size() != 0) {
            try {
                FxmlUtils.showScreen("playerMovesView.fxml", OrlogApplication.getMainStage(), 1200,800, "Player moves");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            Alert canNotAccessPlayerMoves = new Alert(Alert.AlertType.INFORMATION);
            canNotAccessPlayerMoves.setTitle("Access denied");
            canNotAccessPlayerMoves.setHeaderText("No match played this session!");
            canNotAccessPlayerMoves.setContentText("Can not access player moves page because there is nothing to show.");
            canNotAccessPlayerMoves.showAndWait();
        }

    }

}
