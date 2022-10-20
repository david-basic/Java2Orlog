package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.DiceDetails;
import hr.algebra.java2orlog.models.DiceSymbols;
import hr.algebra.java2orlog.models.PlayerDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameScreenController implements Initializable {
    //region Fields
    private Boolean playerOneTurn;
    private Boolean playingIsDone;
    private int cntAxeP1 = 0, cntShieldP1 = 0, cntShield_SP1 = 0, cntHelmetP1 = 0, cntHelmet_SP1 = 0, cntArrowP1 = 0, cntArrow_SP1 = 0, cntHandP1 = 0, cntHand_SP1 = 0;
    private int cntAxeP2 = 0, cntShieldP2 = 0, cntShield_SP2 = 0, cntHelmetP2 = 0, cntHelmet_SP2 = 0, cntArrowP2 = 0, cntArrow_SP2 = 0, cntHandP2 = 0, cntHand_SP2 = 0;
    private int rollCount = 0;
    private int roundCount = 1;
    private int playerOneCoinCount = 0;
    private int playerTwoCoinCount = 0;
    private int playerOneTotalDamageTaken = 0;
    private int playerTwoTotalDamageTaken = 0;

    private List<DiceDetails> notChosenDice = new ArrayList<>();
    private List<DiceDetails> playerOneAllDice = new ArrayList<>();
    private List<DiceDetails> playerTwoAllDice = new ArrayList<>();

    private List<DiceSymbols> die1Symbols = new ArrayList<>();
    private List<DiceSymbols> die2Symbols = new ArrayList<>();
    private List<DiceSymbols> die3Symbols = new ArrayList<>();
    private List<DiceSymbols> die4Symbols = new ArrayList<>();
    private List<DiceSymbols> die5Symbols = new ArrayList<>();
    private List<DiceSymbols> die6Symbols = new ArrayList<>();
    //endregion

    //region FXML elements player one
    @FXML
    private Button btnPlayerOneGodPower1;
    @FXML
    private Button btnPlayerOneGodPower2;
    @FXML
    private Button btnPlayerOneGodPower3;
    @FXML
    private Label lblPlayerOneName;
    @FXML
    private Circle turnCoinPlayerOne;
    @FXML
    private Button btnPlayerOneTrayDice1;
    @FXML
    private Button btnPlayerOneTrayDice2;
    @FXML
    private Button btnPlayerOneTrayDice3;
    @FXML
    private Button btnPlayerOneTrayDice4;
    @FXML
    private Button btnPlayerOneTrayDice5;
    @FXML
    private Button btnPlayerOneTrayDice6;
    @FXML
    private Button btnRollDicePlayerOne;
    @FXML
    private Button btnEndTurnPlayerOne;
    @FXML
    private HBox hbPlayerOneChosenDice;
    @FXML
    private Label lblPlayerOneCoins;
    @FXML
    private ImageView imgCoinPlayerOne;
    @FXML
    private GridPane gridPanePlayerOneHealth;
    //endregion
    //region FXML elements player two
    @FXML
    private Button btnPlayerTwoGodPower1;
    @FXML
    private Button btnPlayerTwoGodPower2;
    @FXML
    private Button btnPlayerTwoGodPower3;
    @FXML
    private Label lblPlayerTwoName;
    @FXML
    private Circle turnCoinPlayerTwo;
    @FXML
    private Button btnPlayerTwoTrayDice1;
    @FXML
    private Button btnPlayerTwoTrayDice2;
    @FXML
    private Button btnPlayerTwoTrayDice3;
    @FXML
    private Button btnPlayerTwoTrayDice4;
    @FXML
    private Button btnPlayerTwoTrayDice5;
    @FXML
    private Button btnPlayerTwoTrayDice6;
    @FXML
    private Button btnRollDicePlayerTwo;
    @FXML
    private Button btnEndTurnPlayerTwo;
    @FXML
    private HBox hbPlayerTwoChosenDice;
    @FXML
    private Label lblPlayerTwoCoins;
    @FXML
    private ImageView imgCoinPlayerTwo;
    @FXML
    private GridPane gridPanePlayerTwoHealth;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerOneTurn = true;
        turnCoinPlayerOne.setVisible(true);
        turnCoinPlayerTwo.setVisible(false);

        btnRollDicePlayerOne.setVisible(true);
        btnRollDicePlayerTwo.setVisible(false);

        Image imgCoin = new Image(Objects.requireNonNull(getClass().getResource("/Coin.jpg")).toExternalForm());
        setCoinImages(imgCoin, imgCoinPlayerOne);
        setCoinImages(imgCoin, imgCoinPlayerTwo);
        lblPlayerOneCoins.setText("0");
        lblPlayerTwoCoins.setText("0");

        lblPlayerOneName.setText(LoginController.getPlayerOneDetails().getPlayerName());
        lblPlayerTwoName.setText(LoginController.getPlayerTwoDetails().getPlayerName());

        defineAllDice();

        roundSetup(playerOneAllDice);
        roundSetup(playerTwoAllDice);
        playerOneAllDice.forEach(d -> d.getDiceButton().setDisable(true));
        playerTwoAllDice.forEach(d -> d.getDiceButton().setDisable(true));

    }

    private void setCoinImages(Image imgCoin, ImageView imgCoinPlayerContainer) {
        imgCoinPlayerContainer.setImage(imgCoin);
        imgCoinPlayerContainer.setDisable(false);
        imgCoinPlayerContainer.setVisible(true);
    }

    //region Dice definition
    private void defineAllDice() {
        if (roundCount == 1) {
            defineAllDiceSymbols();
        }
        playerDiceDefine(playerOneAllDice, btnPlayerOneTrayDice1, btnPlayerOneTrayDice2, btnPlayerOneTrayDice3, btnPlayerOneTrayDice4, btnPlayerOneTrayDice5, btnPlayerOneTrayDice6);
        playerDiceDefine(playerTwoAllDice, btnPlayerTwoTrayDice1, btnPlayerTwoTrayDice2, btnPlayerTwoTrayDice3, btnPlayerTwoTrayDice4, btnPlayerTwoTrayDice5, btnPlayerTwoTrayDice6);
    }

    private void defineAllDiceSymbols() {
        die1Symbols.add(DiceSymbols.Axe);
        die1Symbols.add(DiceSymbols.Axe);
        die1Symbols.add(DiceSymbols.Shield);
        die1Symbols.add(DiceSymbols.Arrow_S);
        die1Symbols.add(DiceSymbols.Helmet);
        die1Symbols.add(DiceSymbols.Hand_S);

        die2Symbols.add(DiceSymbols.Axe);
        die2Symbols.add(DiceSymbols.Axe);
        die2Symbols.add(DiceSymbols.Shield_S);
        die2Symbols.add(DiceSymbols.Arrow);
        die2Symbols.add(DiceSymbols.Helmet);
        die2Symbols.add(DiceSymbols.Hand_S);

        die3Symbols.add(DiceSymbols.Axe);
        die3Symbols.add(DiceSymbols.Axe);
        die3Symbols.add(DiceSymbols.Shield);
        die3Symbols.add(DiceSymbols.Arrow_S);
        die3Symbols.add(DiceSymbols.Helmet_S);
        die3Symbols.add(DiceSymbols.Hand);

        die4Symbols.add(DiceSymbols.Axe);
        die4Symbols.add(DiceSymbols.Axe);
        die4Symbols.add(DiceSymbols.Shield);
        die4Symbols.add(DiceSymbols.Arrow);
        die4Symbols.add(DiceSymbols.Helmet_S);
        die4Symbols.add(DiceSymbols.Hand_S);

        die5Symbols.add(DiceSymbols.Axe);
        die5Symbols.add(DiceSymbols.Axe);
        die5Symbols.add(DiceSymbols.Shield_S);
        die5Symbols.add(DiceSymbols.Arrow_S);
        die5Symbols.add(DiceSymbols.Helmet);
        die5Symbols.add(DiceSymbols.Hand);

        die6Symbols.add(DiceSymbols.Axe);
        die6Symbols.add(DiceSymbols.Axe);
        die6Symbols.add(DiceSymbols.Shield_S);
        die6Symbols.add(DiceSymbols.Arrow);
        die6Symbols.add(DiceSymbols.Helmet_S);
        die6Symbols.add(DiceSymbols.Hand);
    }

    private void playerDiceDefine(List<DiceDetails> player, Button btnDice1, Button btnDice2, Button btnDice3, Button btnDice4, Button btnDice5, Button btnDice6) {
        player.add(new DiceDetails(btnDice1, die1Symbols, false, false));
        player.add(new DiceDetails(btnDice2, die2Symbols, false, false));
        player.add(new DiceDetails(btnDice3, die3Symbols, false, false));
        player.add(new DiceDetails(btnDice4, die4Symbols, false, false));
        player.add(new DiceDetails(btnDice5, die5Symbols, false, false));
        player.add(new DiceDetails(btnDice6, die6Symbols, false, false));
    }

    //endregion
    private void roundSetup(List<DiceDetails> Dice) {
        for (var d : Dice) {
            if (!d.getIsChosenFromDiceTray()) {
                var symbols = d.getDiceSymbols();
                setButtonImage(d.getDiceButton(), symbols);
            }
        }

        for (var d : Dice) {
            if (!d.getIsChosenFromDiceTray()) {
                setDiceButtonVisible(d.getDiceButton());
            }
        }
    }

    private void setButtonImage(Button button, List<DiceSymbols> symbols) {
//        ImageView diceImage = new ImageView(getClass().getResource("/" + symbols.get(0).toString().trim() + ".jpg").toExternalForm()); // isto kao ovo ispod
        ImageView diceImage = new ImageView(Objects.requireNonNull(getClass().getResource("/" + symbols.get(0).toString().trim() + ".jpg")).toExternalForm());
        diceImage.setFitHeight(60);
        diceImage.setFitWidth(60);
        button.setPadding(new Insets(0, 0, 0, 0));
        button.setGraphic(diceImage);
    }

    private void setDiceButtonVisible(Button btnDice) {
        btnDice.setVisible(true);
    }


    @FXML
    public void rollTheDice() {

        notChosenDice.clear();

        if (playerOneTurn) { // P1 turn
            fillNotChosenDiceList(playerOneAllDice);
            if (notChosenDice.size() != 0) { // ako ima kockica u trayu od P1
                shuffleDiceSymbols(playerOneAllDice);
                roundSetup(playerOneAllDice); // set images to buttons in tray and set only those in tray visible
                setAllDiceButtonsInCollectionEnabled(playerOneAllDice);
                btnRollDicePlayerOne.setVisible(false);
                btnEndTurnPlayerOne.setVisible(true);
            } else { // ako nema kockica u trayu od P1 kada je turn P1, onda igra P2 odmah
                playerOneTurn = false;
            }
        } else { // P2 turn
            fillNotChosenDiceList(playerTwoAllDice);
            if (notChosenDice.size() != 0) { // ako ima kockica u trayu od P2
                shuffleDiceSymbols(playerTwoAllDice);
                roundSetup(playerTwoAllDice); // set images to buttons in tray and set only those in tray visible
                setAllDiceButtonsInCollectionEnabled(playerTwoAllDice);
                btnRollDicePlayerTwo.setVisible(false);
                btnEndTurnPlayerTwo.setVisible(true);
            } else { // ako nema kockica u trayu od P2 kada je P2 na redu, onda igra P1 odmah
                playerOneTurn = true;
            }
        }
    }

    private void fillNotChosenDiceList(List<DiceDetails> allDice) {
        allDice.forEach(d ->
                {
                    if (!d.getIsChosenFromDiceTray()) {
                        notChosenDice.add(d); // for each die from all, if it has not been chosen, add it to not chosen die list
                    }
                }
        );
    }

    private void shuffleDiceSymbols(List<DiceDetails> Dice) {
        for (var d : Dice) {
            List<DiceSymbols> symbolsCollection = new ArrayList<>(d.getDiceSymbols());
            if (!d.getIsChosenFromDiceTray()) {
                Collections.shuffle(symbolsCollection);
            }
            d.setDiceSymbols(symbolsCollection);
        }
    }

    private void setAllDiceButtonsInCollectionEnabled(List<DiceDetails> Dice) {
        for (var d : Dice) {
            if (!d.getIsChosenFromDiceTray()) {
                setDiceButtonEnabled(d.getDiceButton());
            }
        }
    }

    private void setDiceButtonEnabled(Button btnDice) {
        btnDice.setDisable(false);
    }


    @FXML
    public void endTurn() {
        if (rollCount == 8) { // ako je rollcount jednak 8 kada se stisne end turn

            sendAllRemainingNotChosenDiceToCenter(playerOneAllDice);
            sendAllRemainingNotChosenDiceToCenter(playerTwoAllDice);
            fillCenterWithSymbols(hbPlayerOneChosenDice, playerOneAllDice);
            fillCenterWithSymbols(hbPlayerTwoChosenDice, playerTwoAllDice);

            roundResolve(); // end the round or the game

            newRoundAlert();
            if (!playingIsDone){
                Alert newRoundAlert = new Alert(Alert.AlertType.INFORMATION);
                newRoundAlert.setTitle("ROUND END");
                newRoundAlert.setHeaderText(null);
                newRoundAlert.setContentText("Round " + (roundCount + 1) + "/6 is about to start!");
                newRoundAlert.showAndWait();
            }

            setupNewRound();
        } else {

            notChosenDice.clear();
            if (playerOneTurn) { // P1 turn
                fillNotChosenDiceList(playerOneAllDice);
                if (notChosenDice.size() != 0) { // ako ima kockica u P1 trayu kada je korisnik stisnuo end turn
                    notChosenDice.clear();
                    fillNotChosenDiceList(playerTwoAllDice);
                    if (notChosenDice.size() != 0) { // provjeri dal ima diceva u P2 trayu, ako ima game nastavlja normalno
                        btnEndTurnPlayerOne.setVisible(false);
                        btnRollDicePlayerTwo.setVisible(true);
                        playerOneTurn = !playerOneTurn;

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerOneAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        notChosenDice.clear();

                    } else { // ako nema diceva u P2 trayu a ima u P1 trayu, onda P1 igra opet
                        playerOneTurn = true;
                        btnRollDicePlayerOne.setVisible(true);
                        btnEndTurnPlayerOne.setVisible(false);

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerOneAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        notChosenDice.clear();
                    }
                } else { // ako nema dice-eva u P1 trayu kada je korisnik stisnuo end
                    notChosenDice.clear();
                    fillNotChosenDiceList(playerTwoAllDice);
                    if (notChosenDice.size() != 0) { // ako ima diceva u P2 trayu dok u P1 nema, P2 igra opet
                        playerOneTurn = false;
                        btnRollDicePlayerTwo.setVisible(true);
                        btnEndTurnPlayerOne.setVisible(false);
                        btnEndTurnPlayerTwo.setVisible(false);

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerTwoAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        notChosenDice.clear();
                    } else { // ako nema diceva u P1 i nema Diceva u P2 kada je P1 stisnuo end kraj runde ili igre
                        roundResolve();

                        newRoundAlert();

                        setupNewRound();
                    }
                }
            } else { // P2 turn
                fillNotChosenDiceList(playerTwoAllDice);
                if (notChosenDice.size() != 0) { // ako ima dice-eva u P2 trayu kada je korisnik stisnuo end
                    notChosenDice.clear();
                    fillNotChosenDiceList(playerOneAllDice);
                    if (notChosenDice.size() != 0) { // provjeri dal ima diceva u P1 trayu, ako ima continue game
                        btnEndTurnPlayerTwo.setVisible(false);
                        btnRollDicePlayerOne.setVisible(true);
                        playerOneTurn = !playerOneTurn;

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerTwoAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        notChosenDice.clear();
                    } else { // ako nema kockica u P1 trayu, a ima u P2, P2 igra opet
                        playerOneTurn = false;
                        btnRollDicePlayerTwo.setVisible(true);
                        btnEndTurnPlayerTwo.setVisible(false);

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerTwoAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        notChosenDice.clear();
                    }
                } else { // ako nema kockica u P2 trayu kada korisnik stisne end
                    notChosenDice.clear();
                    fillNotChosenDiceList(playerOneAllDice);
                    if (notChosenDice.size() != 0) { // ako ima kockica u P1 dok nema u P2, P1 igra opet
                        playerOneTurn = true;
                        btnRollDicePlayerOne.setVisible(true);
                        btnEndTurnPlayerOne.setVisible(false);
                        btnEndTurnPlayerTwo.setVisible(false);

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerOneAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        notChosenDice.clear();
                    } else { // ako nema u P2 trayu i nema u P1 trayu kada P2 stisne end Kraj runde ili igre
                        roundResolve();

                        Alert newRoundAlert = new Alert(Alert.AlertType.INFORMATION);
                        newRoundAlert.setTitle("ROUND END");
                        newRoundAlert.setHeaderText(null);
                        newRoundAlert.setContentText("Round " + (roundCount + 1) + "/6 is about to start!");
                        newRoundAlert.showAndWait();

                        setupNewRound();
                    }
                }
            }

            if (playerOneTurn) {
                turnCoinPlayerOne.setVisible(true);
                turnCoinPlayerTwo.setVisible(false);
            } else {
                turnCoinPlayerOne.setVisible(false);
                turnCoinPlayerTwo.setVisible(true);
            }
        }

        notChosenDice.clear();
        rollCount++;
    }


    private void sendAllRemainingNotChosenDiceToCenter(List<DiceDetails> diceCollection) {
        for (var d : diceCollection) {
            if (!d.getIsChosenFromDiceTray()) {
                d.setIsChosenFromDiceTray(true);
                d.setCanBeSentToCenter(true);
            }
        }
    }

    private void fillCenterWithSymbols(HBox hbCentralContainer, List<DiceDetails> diceCollection) {
        diceCollection.forEach(d -> {
            if (d.getCanBeSentToCenter()) {
                String symbol = d.getDiceSymbols().get(0).toString().trim();
                ImageView symbolImage = new ImageView(Objects.requireNonNull(getClass().getResource("/" + symbol + ".jpg")).toExternalForm());
                symbolImage.setFitWidth(60);
                symbolImage.setFitHeight(60);
                hbCentralContainer.getChildren().add(
                        symbolImage
                );
                d.getDiceButton().setVisible(false); // TODO: 20/10/2022 novo dodano da probam fixat zaostale diceve vidljive u trayu
            }
            d.setCanBeSentToCenter(false);
        });
    }

    private void roundResolve() {
        btnRollDicePlayerOne.setVisible(false);
        btnRollDicePlayerTwo.setVisible(false);
        btnEndTurnPlayerOne.setVisible(false);
        btnEndTurnPlayerTwo.setVisible(false);

        //region Damage calculations and logic
        for (var d : playerOneAllDice) {
            List<DiceSymbols> symbols = d.getDiceSymbols();
            int symbolOrdinal = symbols.get(0).ordinal();
            switch (symbolOrdinal) {
                case 0 -> cntAxeP1++;
                case 1 -> cntHelmetP1++;
                case 2 -> cntHelmet_SP1++;
                case 3 -> cntArrowP1++;
                case 4 -> cntArrow_SP1++;
                case 5 -> cntShieldP1++;
                case 6 -> cntShield_SP1++;
                case 7 -> cntHandP1++;
                case 8 -> cntHand_SP1++;
            }
        }

        for (var d : playerTwoAllDice) {
            List<DiceSymbols> symbols = d.getDiceSymbols();
            int symbolOrdinal = symbols.get(0).ordinal();
            switch (symbolOrdinal) {
                case 0 -> cntAxeP2++;
                case 1 -> cntHelmetP2++;
                case 2 -> cntHelmet_SP2++;
                case 3 -> cntArrowP2++;
                case 4 -> cntArrow_SP2++;
                case 5 -> cntShieldP2++;
                case 6 -> cntShield_SP2++;
                case 7 -> cntHandP2++;
                case 8 -> cntHand_SP2++;
            }
        }

        var axeDamageGivenByP1 = cntAxeP1;
        var axeDamageGivenByP2 = cntAxeP2;

        var arrowDamageGivenByP1 = cntArrowP1 + cntArrow_SP1;
        var arrowDamageGivenByP2 = cntArrowP2 + cntArrow_SP2;

        var damageMitigatedByHelmetsP1 = cntHelmetP1 + cntHelmet_SP1;
        var damageMitigatedByHelmetsP2 = cntHelmetP2 + cntHelmet_SP2;

        var damageMitigatedByShieldsP1 = cntShieldP1 + cntShield_SP1;
        var damageMitigatedByShieldsP2 = cntShieldP2 + cntShield_SP2;

        if (damageMitigatedByHelmetsP1 < axeDamageGivenByP2) {
            playerOneTotalDamageTaken += axeDamageGivenByP2 - damageMitigatedByHelmetsP1;
        }
        if (damageMitigatedByShieldsP1 < arrowDamageGivenByP2) {
            playerOneTotalDamageTaken += arrowDamageGivenByP2 - damageMitigatedByShieldsP1;
        }
        if (damageMitigatedByHelmetsP2 < axeDamageGivenByP1) {
            playerTwoTotalDamageTaken += axeDamageGivenByP1 - damageMitigatedByHelmetsP2;
        }
        if (damageMitigatedByShieldsP2 < arrowDamageGivenByP1) {
            playerTwoTotalDamageTaken += arrowDamageGivenByP1 - damageMitigatedByShieldsP2;
        }

        var p1HP = gridPanePlayerOneHealth.getChildren().stream().toList();
        var p2HP = gridPanePlayerTwoHealth.getChildren().stream().toList();

        int i = 0;
        for (var hp : p1HP) {
            if (i == playerOneTotalDamageTaken) {
                break;
            }
            hp.setVisible(false);
            i++;
        }

        i = 0;
        for (var hp : p2HP) {
            if (i == playerTwoTotalDamageTaken) {
                break;
            }
            hp.setVisible(false);
            i++;
        }
        //endregion

        if (checkIfTheGameJustEnded(playerOneTotalDamageTaken, playerTwoTotalDamageTaken)) { // provjeri dal je neki od igraca pokupio 15 ili vise damage-a prije god powersa
            recordWins(playerOneTotalDamageTaken, playerTwoTotalDamageTaken, LoginController.getPlayerOneDetails(), LoginController.getPlayerTwoDetails());
        }

        if (!checkIfTheGameJustEnded(playerOneTotalDamageTaken, playerTwoTotalDamageTaken)) {
            //region Coin calculations and logic
            var coinsStolenByP1 = cntHandP1 + cntHand_SP1;
            var coinsStolenByP2 = cntHandP2 + cntHand_SP2;

            playerOneCoinCount += cntShield_SP1 + cntHelmet_SP1 + cntArrow_SP1 + cntHand_SP1;
            playerTwoCoinCount += cntShield_SP2 + cntHelmet_SP2 + cntArrow_SP2 + cntHand_SP2;

            if (coinsStolenByP1 <= playerTwoCoinCount) {
                playerOneCoinCount += coinsStolenByP1;
                playerTwoCoinCount -= coinsStolenByP1;
            } else {
                // TODO: 20/10/2022 provjerava slucaj kada ima vise handova od coinova, gdje onda krade sve coinove, NEEDS TESTING!!
                playerOneCoinCount += playerTwoCoinCount;
                playerTwoCoinCount = 0;
            }

            if (coinsStolenByP2 <= playerOneCoinCount) {
                playerTwoCoinCount += coinsStolenByP2;
                playerOneCoinCount -= coinsStolenByP2;
            } else {
                playerTwoCoinCount += playerOneCoinCount;
                playerOneCoinCount = 0;
            }

            String p1Coins = Integer.toString(playerOneCoinCount);
            lblPlayerOneCoins.setText(p1Coins);

            String p2Coins = Integer.toString(playerTwoCoinCount);
            lblPlayerTwoCoins.setText(p2Coins);
            //endregion

            // TODO: 18/10/2022  nakon sto se dodijeli novac mozes usat god powerse ako je useru ostalo novaca tokom runde i dodijelit damage novi ako se taj power usa

            if (checkIfTheGameJustEnded(playerOneTotalDamageTaken, playerTwoTotalDamageTaken)) {
                recordWins(playerOneTotalDamageTaken, playerTwoTotalDamageTaken, LoginController.getPlayerOneDetails(), LoginController.getPlayerTwoDetails());
            }
        }
    }

    private void newRoundAlert() {
        if (!playingIsDone){
            Alert newRoundAlert = new Alert(Alert.AlertType.INFORMATION);
            newRoundAlert.setTitle("ROUND END");
            newRoundAlert.setHeaderText(null);
            newRoundAlert.setContentText("Round " + (roundCount + 1) + "/6 is about to start!");
            newRoundAlert.showAndWait();
        }
    }

    private void setupNewRound() {
        roundCount++;
        if (roundCount % 2 == 0) { // ako je sljedeca runda parna igra prvi u rundi P2
            playerOneTurn = false;

            setupDiceForNewRound();

            btnRollDicePlayerOne.setVisible(false);
            btnRollDicePlayerTwo.setVisible(true);
        } else { // inace igra P1
            playerOneTurn = true;

            setupDiceForNewRound();

            btnRollDicePlayerOne.setVisible(true);
            btnRollDicePlayerTwo.setVisible(false);
        }

        btnEndTurnPlayerOne.setVisible(false);
        btnEndTurnPlayerTwo.setVisible(false);

        if (playerOneTurn) {
            turnCoinPlayerOne.setVisible(true);
            turnCoinPlayerTwo.setVisible(false);
        } else {
            turnCoinPlayerOne.setVisible(false);
            turnCoinPlayerTwo.setVisible(true);
        }

        resetSymbolCounters();
        rollCount = 0;
    }

    private Boolean checkIfTheGameJustEnded(int playerOneTotalDamageTaken, int playerTwoTotalDamageTaken) {
        // game ends when one or both players die or when the round count reaches max
        return playerOneTotalDamageTaken >= 15 || playerTwoTotalDamageTaken >= 15 || roundCount == 6;
    }

    private void recordWins(int playerOneTotalDamageTaken, int playerTwoTotalDamageTaken, PlayerDetails player1, PlayerDetails player2) {
        if (playerOneTotalDamageTaken >= 15 && playerTwoTotalDamageTaken >= 15) { // Draw
            player1.recordADraw();
            player2.recordADraw();
        } else if (playerOneTotalDamageTaken >= 15) { // P2 wins
            player2.recordAWin();
        } else { // P1 wins
            player1.recordAWin();
        }

        Alert startNewGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
        startNewGameAlert.setTitle("GAME OVER");
        startNewGameAlert.setHeaderText(null);
        startNewGameAlert.setContentText("Do you want to CONTINUE and start a NEW game?");
        Optional<ButtonType> result = startNewGameAlert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            Alert currentScoreAlert = new Alert(Alert.AlertType.INFORMATION);
            currentScoreAlert.setTitle("CURRENT SCORE");
            currentScoreAlert.setHeaderText(player1.getPlayerName() + " VS. " + player2.getPlayerName());
            currentScoreAlert.setContentText(player1.getNumberOfWins().toString() + " : " + player2.getNumberOfWins().toString());
            currentScoreAlert.showAndWait();

            setupNewGame();
        } else {
            FeatureNotYetImplementedAlert();
            // TODO: 19/10/2022 ovdje otvori high score view gdje ce biti ispisani samo kao text jedno ispod drugog u tablici nekoj player1 vs player2 i score u formatu X:Y
            // TODO: 19/10/2022 ili ako tablice imaju vec implementirani neki filter po stupcima mozemo definirati stupce PlayerName|Wins|Draws|Losses <---<---<--- cool ideja
            // TODO: 19/10/2022 mozda u CurrentGameDetails spremati bitne neke detalje o igri

            playingIsDone = true;

            openResultsView();

        }
    }

    private void setupDiceForNewRound() {
        playerOneAllDice.forEach(d -> d.setCanBeSentToCenter(false));
        playerOneAllDice.forEach(d -> d.setIsChosenFromDiceTray(false));
        playerTwoAllDice.forEach(d -> d.setCanBeSentToCenter(false));
        playerTwoAllDice.forEach(d -> d.setIsChosenFromDiceTray(false));

        playerOneAllDice.forEach(d -> d.getDiceButton().setVisible(true));
        playerTwoAllDice.forEach(d -> d.getDiceButton().setVisible(true));

        playerOneAllDice.forEach(d -> d.getDiceButton().setDisable(true));
        playerTwoAllDice.forEach(d -> d.getDiceButton().setDisable(true));

        hbPlayerOneChosenDice.getChildren().clear();
        hbPlayerTwoChosenDice.getChildren().clear();
    }

    private void resetSymbolCounters() {
        cntAxeP1 = 0;
        cntArrowP1 = 0;
        cntArrow_SP1 = 0;
        cntShieldP1 = 0;
        cntShield_SP1 = 0;
        cntHelmetP1 = 0;
        cntHelmet_SP1 = 0;
        cntHandP1 = 0;
        cntHand_SP1 = 0;

        cntAxeP2 = 0;
        cntArrowP2 = 0;
        cntArrow_SP2 = 0;
        cntShieldP2 = 0;
        cntShield_SP2 = 0;
        cntHelmetP2 = 0;
        cntHelmet_SP2 = 0;
        cntHandP2 = 0;
        cntHand_SP2 = 0;
    }

    private void setupNewGame() {
        roundCount = 0;
        rollCount = 0;
        playerOneTurn = true;
        btnRollDicePlayerOne.setVisible(true);
        btnRollDicePlayerTwo.setVisible(false);

        resetSymbolCounters();

        playerOneCoinCount = 0;
        playerTwoCoinCount = 0;
        lblPlayerOneCoins.setText("0");
        lblPlayerTwoCoins.setText("0");

        gridPanePlayerOneHealth.getChildren().stream().toList().forEach(hp -> hp.setVisible(true));
        gridPanePlayerTwoHealth.getChildren().stream().toList().forEach(hp -> hp.setVisible(true));

        btnEndTurnPlayerOne.setVisible(false);
        btnEndTurnPlayerTwo.setVisible(false);

        setupDiceForNewRound();
    }

    private void FeatureNotYetImplementedAlert() {
        Alert featureNotImplemented = new Alert(Alert.AlertType.INFORMATION);
        featureNotImplemented.setTitle("WIP");
        featureNotImplemented.setHeaderText("WIP");
        featureNotImplemented.setContentText("Feature not yet implemented. WIP!");
        featureNotImplemented.showAndWait();
    }
    private void openResultsView(){
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


    @FXML
    public void diceChosen(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        if (playerOneTurn) {

            setButtonInvisibleAndTagItAsChosen(playerOneAllDice, clickedButton);

            fillCenterWithSymbols(hbPlayerOneChosenDice, playerOneAllDice);
        } else {
            setButtonInvisibleAndTagItAsChosen(playerTwoAllDice, clickedButton);

            fillCenterWithSymbols(hbPlayerTwoChosenDice, playerTwoAllDice);
        }
    }

    private void setButtonInvisibleAndTagItAsChosen(List<DiceDetails> allDice, Button clickedButton) {
        clickedButton.setVisible(false);
        for (var d : allDice) {
            if (d.getDiceButton().equals(clickedButton)) {
                d.setIsChosenFromDiceTray(true);
                d.setCanBeSentToCenter(true);
            }
        }
    }


    @FXML
    private void clickOnGodPower(ActionEvent actionEvent) {
        Button clickedGodPower = (Button) actionEvent.getSource();

        // TODO: 20/10/2022 implement god powers
    }


}
