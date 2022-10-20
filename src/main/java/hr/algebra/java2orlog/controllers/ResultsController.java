package hr.algebra.java2orlog.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    //region Fields

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

    }

    @FXML
    private void showRulesImage(){

    }

}
