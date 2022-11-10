package hr.algebra.java2orlog.utils;

import hr.algebra.java2orlog.OrlogApplication;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlUtils {
    public static void showScreen(String fxmlFileName, Stage stage, int width, int height, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrlogApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
