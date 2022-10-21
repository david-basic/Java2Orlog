package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerMovesController implements Initializable {
    private Integer oldRoundNumber;
    @FXML
    private TextArea taPlayerMovesDisplayArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (var move : GameScreenController.getPlayerMovesCollection()) {
            StringBuilder sb = new StringBuilder();

            oldRoundNumber = move.getRoundNumber();

            sb.append("Round ").append(move.getRoundNumber()).append("! \n");

            if (oldRoundNumber != move.getRoundNumber()) {
                oldRoundNumber = move.getRoundNumber();
                if (move.getRoundNumber() % 2 != 0) { // ako je runda neparna znam da je player 1 on turn inace je player 2
                    sb.append(move.getPlayer1Name()).append("'s turn: \n");
                } else {
                    sb.append(move.getPlayer2Name()).append("'s turn: \n");
                }
            } else {
                if (move.getRoundNumber() % 2 == 0) { // ako je runda parna znam da je player 1 on turn inace je player 2 kad se promijeni nakon prve rune
                    sb.append(move.getPlayer1Name()).append("'s turn: \n");
                } else {
                    sb.append(move.getPlayer2Name()).append("'s turn: \n");
                }
            }

            sb.append("    Dice played: ");
            for (var d : move.getSymbolsPlayerOnTurnPlayed()) {
                sb.append(d).append(" | "); // TODO: 21/10/2022 mozda dodati to string ako ne bude radilo samo ovako, iako trebalo bi
            }
            sb.append("\n");

            sb.append("    Dice not played: ");
            for (var d : move.getSymbolsPlayerOnTurnDidntPlay()) {
                sb.append(d).append(" | ");
            }
            sb.append("\n");

            if (oldRoundNumber != move.getRoundNumber()) { // ako je ista runa
                if (move.getRoundNumber() % 2 != 0) { // ako je p1 on turn
                    sb.append("    Player has taken ").append(move.getPlayer1DamageTaken().toString()).append(".\n");
                    sb.append("    Player has given ").append(move.getPlayer2DamageTaken().toString()).append(".\n");
                    sb.append("    Player has collected ").append(move.getPlayer1CurrentCoins().toString()).append(" coin so far.\n");
                    sb.append("    Player used following God favor: ").append(move.getPlayer1GodFavorUsed()).append("\n");
                } else {
                    sb.append("    Player has taken ").append(move.getPlayer2DamageTaken().toString()).append(".\n");
                    sb.append("    Player has given ").append(move.getPlayer1DamageTaken().toString()).append(".\n");
                    sb.append("    Player has collected ").append(move.getPlayer2CurrentCoins().toString()).append(" coin so far.\n");
                    sb.append("    Player used following God favor: ").append(move.getPlayer2GodFavorUsed()).append("\n");
                }
            } else { // razlicita runda
                if (move.getRoundNumber() % 2 == 0) { // ako je p2 on turn
                    sb.append("    Player has taken ").append(move.getPlayer2DamageTaken().toString()).append(".\n");
                    sb.append("    Player has given ").append(move.getPlayer1DamageTaken().toString()).append(".\n");
                    sb.append("    Player has collected ").append(move.getPlayer2CurrentCoins().toString()).append(" coin so far.\n");
                    sb.append("    Player used following God favor: ").append(move.getPlayer2GodFavorUsed()).append("\n");
                } else {
                    sb.append("    Player has taken ").append(move.getPlayer1DamageTaken().toString()).append(".\n");
                    sb.append("    Player has given ").append(move.getPlayer2DamageTaken().toString()).append(".\n");
                    sb.append("    Player has collected ").append(move.getPlayer1CurrentCoins().toString()).append(" coin so far.\n");
                    sb.append("    Player used following God favor: ").append(move.getPlayer1GodFavorUsed()).append("\n");
                }
            }

            if (move.getGameOver()) {
                sb.append("GAME OVER\n");
                if (move.getWinnerNameOrDraw().trim() == "Draw") {
                    sb.append("It's a ").append(move.getWinnerNameOrDraw()).append("\n");
                } else {
                    sb.append(move.getWinnerNameOrDraw().trim()).append(" WINS!\n");
                    sb.append("--CONGRATULATIONS--\n");
                }
            }
        }
    }
    // za new line -> sb.append(System.getProperty("line.separator")); ili r.append("\n");

    /*
    playerMoves.add(new MoveDetails(
                roundCount,
                rollCount,
                lblPlayerOneName.toString(),
                lblPlayerTwoName.toString(),
                symbolsPlayed,
                symbolsNotPlayed,
                playerOneTotalDamageTaken,
                playerTwoTotalDamageTaken,
                playerOneCoinCount,
                playerTwoCoinCount,
                "None",
                "None",
                true,
                true,
                winnerOrDraw
        ));
    */

    @FXML
    private void returnToRulesView() {
        FXMLLoader fxmlLoader = new FXMLLoader(OrlogApplication.class.getResource("resultsView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Stage resultsScreenStage = OrlogApplication.getMainStage();
        resultsScreenStage.setResizable(false);
        resultsScreenStage.setTitle("Results");
        resultsScreenStage.setScene(scene);
        resultsScreenStage.show();

        // with this you set the screen dead center in the visual area
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        resultsScreenStage.setX((screenBounds.getWidth() - resultsScreenStage.getWidth()) / 2);
        resultsScreenStage.setY((screenBounds.getHeight() - resultsScreenStage.getHeight()) / 2);
    }
}
