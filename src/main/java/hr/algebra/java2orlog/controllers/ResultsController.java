package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    //region Fields
    private Popup popup = new Popup();
    private Stage stage = OrlogApplication.getMainStage();
    //endregion

    //region FXML elements
    @FXML
    private TableView tblRanking;
    @FXML
    private TableColumn colPlayerName;
    @FXML
    private TableColumn colPlayerWins;
    @FXML
    private TableColumn colPlayerDraws;
    @FXML
    private TableColumn colPlayerLoses;
    @FXML
    private Button btnShowRules;
    //endregion
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    private void showRulesImage(){
        if (!popup.isShowing()){
            popup.show(stage);
        }
    }

}
