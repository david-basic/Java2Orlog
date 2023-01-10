package hr.algebra.java2orlog;

import hr.algebra.java2orlog.utils.FxmlUtils;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class OrlogApplication extends Application {
    private static Stage mainStage;
    private static String playerName;
    private static String playerOrderChoice;

    @Override
    public void start(Stage stage) throws IOException {
        FxmlUtils.showScreen("loginView.fxml", stage, 858, 438, "Login");
        mainStage = stage;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static String getPlayerOrderChoice(){
        return playerOrderChoice;
    }

    public static void main(String[] args) {
        launch();
    }
}