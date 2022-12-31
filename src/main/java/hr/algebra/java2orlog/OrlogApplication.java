package hr.algebra.java2orlog;

import hr.algebra.java2orlog.utils.FxmlUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class OrlogApplication extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        FxmlUtils.showScreen("loginView.fxml", stage, 858, 438, "Login");
        mainStage = stage;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {

        for (String param : args) {
            System.out.println("Next param: " + param);
        }

        launch();
    }
}