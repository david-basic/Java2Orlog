package hr.algebra.java2orlog.controllers;

import hr.algebra.java2orlog.OrlogApplication;
import hr.algebra.java2orlog.models.*;
import hr.algebra.java2orlog.utils.FxmlUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GameScreenController implements Initializable {
    //region Fields
    private Boolean playerOneTurn;
    private boolean playingIsDone = false;
    private int cntAxeP1 = 0, cntShieldP1 = 0, cntShield_SP1 = 0, cntHelmetP1 = 0, cntHelmet_SP1 = 0, cntArrowP1 = 0, cntArrow_SP1 = 0, cntHandP1 = 0, cntHand_SP1 = 0;
    private int cntAxeP2 = 0, cntShieldP2 = 0, cntShield_SP2 = 0, cntHelmetP2 = 0, cntHelmet_SP2 = 0, cntArrowP2 = 0, cntArrow_SP2 = 0, cntHandP2 = 0, cntHand_SP2 = 0;
    private int turnCount = 0;
    private int roundCount = 1;
    private int playerOneCoinCount = 0;
    private int playerTwoCoinCount = 0;
    private int playerOneTotalDamageTaken = 0;
    private int playerTwoTotalDamageTaken = 0;
    private static final String CLASS_EXTENSION = ".class";
    private Boolean loadAllowed = true;

    private MoveDetails tempMoveDetails;
    private static List<MoveDetails> playerMoves = new ArrayList<>();

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
    private Button btnThorP1;
    @FXML
    private Button btnHelP1;
    @FXML
    private Button btnIdunP1;
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
    @FXML
    private Tooltip ttThorP1;
    @FXML
    private Tooltip ttHelP1;
    @FXML
    private Tooltip ttIdunP1;
    @FXML
    private GridPane gpP1GodFavors;
    @FXML
    private GridPane gridP1AllDice;
    //endregion
    //region FXML elements player two
    @FXML
    private Button btnThorP2;
    @FXML
    private Button btnHelP2;
    @FXML
    private Button btnIdunP2;
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
    @FXML
    private Tooltip ttThorP2;
    @FXML
    private Tooltip ttHelP2;
    @FXML
    private Tooltip ttIdunP2;
    @FXML
    private GridPane gpP2GodFavors;
    @FXML
    private GridPane gridP2AllDice;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerOneTurn = true;
        turnCoinPlayerOne.setVisible(true);
        turnCoinPlayerTwo.setVisible(false);

        btnRollDicePlayerOne.setVisible(true);
        btnRollDicePlayerTwo.setVisible(false);

        addGodFavorImages();

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

        gpP1GodFavors.setDisable(true);
        gpP2GodFavors.setDisable(true);
    }

    private void addGodFavorImages() {
        ImageView p1GodFavor1Img = new ImageView(Objects.requireNonNull(getClass().getResource("/Thors_Wrath.png")).toExternalForm());
        p1GodFavor1Img.setFitHeight(284);
        p1GodFavor1Img.setFitWidth(176);
        btnThorP1.setPadding(new Insets(0, 0, 0, 0));
        btnThorP1.setGraphic(p1GodFavor1Img);

        ImageView p1GodFavor2Img = new ImageView(Objects.requireNonNull(getClass().getResource("/Hels_Grip.png")).toExternalForm());
        p1GodFavor2Img.setFitHeight(284);
        p1GodFavor2Img.setFitWidth(176);
        btnHelP1.setPadding(new Insets(0, 0, 0, 0));
        btnHelP1.setGraphic(p1GodFavor2Img);

        ImageView p1GodFavor3Img = new ImageView(Objects.requireNonNull(getClass().getResource("/Iduns_Rejuvenation.png")).toExternalForm());
        p1GodFavor3Img.setFitHeight(284);
        p1GodFavor3Img.setFitWidth(176);
        btnIdunP1.setPadding(new Insets(0, 0, 0, 0));
        btnIdunP1.setGraphic(p1GodFavor3Img);

        ImageView p2GodFavor1Img = new ImageView(Objects.requireNonNull(getClass().getResource("/Thors_Wrath.png")).toExternalForm());
        p2GodFavor1Img.setFitHeight(284);
        p2GodFavor1Img.setFitWidth(176);
        btnThorP2.setPadding(new Insets(0, 0, 0, 0));
        btnThorP2.setGraphic(p2GodFavor1Img);

        ImageView p2GodFavor2Img = new ImageView(Objects.requireNonNull(getClass().getResource("/Hels_Grip.png")).toExternalForm());
        p2GodFavor2Img.setFitHeight(284);
        p2GodFavor2Img.setFitWidth(176);
        btnHelP2.setPadding(new Insets(0, 0, 0, 0));
        btnHelP2.setGraphic(p2GodFavor2Img);

        ImageView p2GodFavor3Img = new ImageView(Objects.requireNonNull(getClass().getResource("/Iduns_Rejuvenation.png")).toExternalForm());
        p2GodFavor3Img.setFitHeight(284);
        p2GodFavor3Img.setFitWidth(176);
        btnIdunP2.setPadding(new Insets(0, 0, 0, 0));
        btnIdunP2.setGraphic(p2GodFavor3Img);

        ImageView thorTooltipImgP1 = new ImageView(Objects.requireNonNull(getClass().getResource("/Thors_Wrath_explanation.jpg")).toExternalForm());
        thorTooltipImgP1.setFitHeight(44);
        thorTooltipImgP1.setFitWidth(287);
        ttThorP1.setGraphic(thorTooltipImgP1);

        ImageView helTooltipImgP1 = new ImageView(Objects.requireNonNull(getClass().getResource("/Hels_Grip_explanation.jpg")).toExternalForm());
        helTooltipImgP1.setFitHeight(44);
        helTooltipImgP1.setFitWidth(287);
        ttHelP1.setGraphic(helTooltipImgP1);

        ImageView idunTooltipImgP1 = new ImageView(Objects.requireNonNull(getClass().getResource("/Iduns_Rejuvenation_explanation.jpg")).toExternalForm());
        idunTooltipImgP1.setFitHeight(44);
        idunTooltipImgP1.setFitWidth(287);
        ttIdunP1.setGraphic(idunTooltipImgP1);

        ImageView thorTooltipImgP2 = new ImageView(Objects.requireNonNull(getClass().getResource("/Thors_Wrath_explanation.jpg")).toExternalForm());
        thorTooltipImgP2.setFitHeight(44);
        thorTooltipImgP2.setFitWidth(287);
        ttThorP2.setGraphic(thorTooltipImgP2);

        ImageView helTooltipImgP2 = new ImageView(Objects.requireNonNull(getClass().getResource("/Hels_Grip_explanation.jpg")).toExternalForm());
        helTooltipImgP2.setFitHeight(44);
        helTooltipImgP2.setFitWidth(287);
        ttHelP2.setGraphic(helTooltipImgP2);

        ImageView idunTooltipImgP2 = new ImageView(Objects.requireNonNull(getClass().getResource("/Iduns_Rejuvenation_explanation.jpg")).toExternalForm());
        idunTooltipImgP2.setFitHeight(44);
        idunTooltipImgP2.setFitWidth(287);
        ttIdunP2.setGraphic(idunTooltipImgP2);

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
        button.setAccessibleText(symbols.get(0).toString().trim());
        button.setGraphic(diceImage);
    }

    private void setDiceButtonVisible(Button btnDice) {
        btnDice.setVisible(true);
    }


    @FXML
    public void rollTheDice() {
        tempMoveDetails = new MoveDetails(
                0,
                0,
                "John Doe1",
                "John Doe2",
                new ArrayList<>(),
                new ArrayList<>(),
                0,
                0,
                0,
                0,
                "None",
                "None",
                false,
                false,
                "To be determined"
        ); // basic template which is going to be changed accordingly as data changes

        // basic setup
        tempMoveDetails.setRoundNumber(roundCount);
        tempMoveDetails.setTurnNumber(turnCount);
        tempMoveDetails.setPlayer1Name(lblPlayerOneName.getText());
        tempMoveDetails.setPlayer2Name(lblPlayerTwoName.getText());

        notChosenDice.clear();

        if (playerOneTurn) { // P1 turn
            fillNotChosenDiceList(playerOneAllDice);
            if (notChosenDice.size() != 0) { // ako ima kockica u trayu od P1
                shuffleDiceSymbols(playerOneAllDice);
                roundSetup(playerOneAllDice); // set images to buttons in tray and set only those in tray visible
                setAllDiceButtonsInCollectionEnabled(playerOneAllDice);
                setAllGodFavorsInGridPaneEnabled(gpP1GodFavors);
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
                setAllGodFavorsInGridPaneEnabled(gpP2GodFavors);
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

    private void setAllGodFavorsInGridPaneEnabled(GridPane gridPaneArea) {
        gridPaneArea.setDisable(false);
    }

    @FXML
    public void endTurn() {
        if (turnCount == 8) { // ako je turn-count jednak 8 kada se stisne end turn

            sendAllRemainingNotChosenDiceToCenter(playerOneAllDice);
            sendAllRemainingNotChosenDiceToCenter(playerTwoAllDice);
            fillCenterWithSymbols(hbPlayerOneChosenDice, playerOneAllDice);
            fillCenterWithSymbols(hbPlayerTwoChosenDice, playerTwoAllDice);

            roundResolve(); // end the round or the game

            newRoundAlert();

            if (!playingIsDone) {

                setupNewRound();
            }
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
                        setAllGodFavorsInGridPaneDisabled(gpP1GodFavors);
                        notChosenDice.clear();

                    } else { // ako nema diceva u P2 trayu a ima u P1 trayu, onda P1 igra opet
                        playerOneTurn = true;
                        btnRollDicePlayerOne.setVisible(true);
                        btnEndTurnPlayerOne.setVisible(false);

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerOneAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        setAllGodFavorsInGridPaneDisabled(gpP1GodFavors);
                        notChosenDice.clear();
                    }

                    List<DiceDetails> playerOnTurnDice;
                    if (playerOneTurn) {
                        playerOnTurnDice = new ArrayList<>(playerOneAllDice);
                    } else {
                        playerOnTurnDice = new ArrayList<>(playerTwoAllDice);
                    }
                    List<DiceSymbols> symbolsPlayed = new ArrayList<>();
                    List<DiceSymbols> symbolsNotPlayed = new ArrayList<>();
                    for (var d : playerOnTurnDice) {
                        if (d.getIsChosenFromDiceTray()) {
                            symbolsPlayed.add(d.getDiceSymbols().get(0));
                        } else {
                            symbolsNotPlayed.add((d.getDiceSymbols().get(0)));
                        }
                    }
                    tempMoveDetails.setRoundNumber(roundCount);
                    tempMoveDetails.setTurnNumber(turnCount);
                    tempMoveDetails.setSymbolsPlayerOnTurnPlayed(symbolsPlayed);
                    tempMoveDetails.setSymbolsPlayerOnTurnDidntPlay(symbolsNotPlayed);
                    tempMoveDetails.setPlayer1CurrentCoins(playerOneCoinCount);
                    tempMoveDetails.setPlayer2CurrentCoins(playerTwoCoinCount);

                    playerMoves.add(tempMoveDetails);

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
                        setAllGodFavorsInGridPaneDisabled(gpP1GodFavors);

                        List<DiceDetails> playerOnTurnDice;
                        if (playerOneTurn) {
                            playerOnTurnDice = new ArrayList<>(playerOneAllDice);
                        } else {
                            playerOnTurnDice = new ArrayList<>(playerTwoAllDice);
                        }
                        List<DiceSymbols> symbolsPlayed = new ArrayList<>();
                        List<DiceSymbols> symbolsNotPlayed = new ArrayList<>();
                        for (var d : playerOnTurnDice) {
                            if (d.getIsChosenFromDiceTray()) {
                                symbolsPlayed.add(d.getDiceSymbols().get(0));
                            } else {
                                symbolsNotPlayed.add((d.getDiceSymbols().get(0)));
                            }
                        }
                        tempMoveDetails.setRoundNumber(roundCount);
                        tempMoveDetails.setTurnNumber(turnCount);
                        tempMoveDetails.setSymbolsPlayerOnTurnPlayed(symbolsPlayed);
                        tempMoveDetails.setSymbolsPlayerOnTurnDidntPlay(symbolsNotPlayed);
                        tempMoveDetails.setPlayer1CurrentCoins(playerOneCoinCount);
                        tempMoveDetails.setPlayer2CurrentCoins(playerTwoCoinCount);

                        playerMoves.add(tempMoveDetails);
                    } else { // ako nema diceva u P1 i nema Diceva u P2 kada je P1 stisnuo end kraj runde ili igre
                        roundResolve();

                        newRoundAlert();

                        if (!playingIsDone) {
                            setupNewRound();
                        }
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
                        setAllGodFavorsInGridPaneDisabled(gpP2GodFavors);
                    } else { // ako nema kockica u P1 trayu, a ima u P2, P2 igra opet
                        playerOneTurn = false;
                        btnRollDicePlayerTwo.setVisible(true);
                        btnEndTurnPlayerTwo.setVisible(false);

                        notChosenDice.clear();
                        fillNotChosenDiceList(playerTwoAllDice);
                        notChosenDice.forEach(d -> d.getDiceButton().setDisable(true));
                        notChosenDice.clear();
                        setAllGodFavorsInGridPaneDisabled(gpP2GodFavors);
                    }

                    List<DiceDetails> playerOnTurnDice;
                    if (playerOneTurn) {
                        playerOnTurnDice = new ArrayList<>(playerOneAllDice);
                    } else {
                        playerOnTurnDice = new ArrayList<>(playerTwoAllDice);
                    }
                    List<DiceSymbols> symbolsPlayed = new ArrayList<>();
                    List<DiceSymbols> symbolsNotPlayed = new ArrayList<>();
                    for (var d : playerOnTurnDice) {
                        if (d.getIsChosenFromDiceTray()) {
                            symbolsPlayed.add(d.getDiceSymbols().get(0));
                        } else {
                            symbolsNotPlayed.add((d.getDiceSymbols().get(0)));
                        }
                    }
                    tempMoveDetails.setRoundNumber(roundCount);
                    tempMoveDetails.setTurnNumber(turnCount);
                    tempMoveDetails.setSymbolsPlayerOnTurnPlayed(symbolsPlayed);
                    tempMoveDetails.setSymbolsPlayerOnTurnDidntPlay(symbolsNotPlayed);
                    tempMoveDetails.setPlayer1CurrentCoins(playerOneCoinCount);
                    tempMoveDetails.setPlayer2CurrentCoins(playerTwoCoinCount);

                    playerMoves.add(tempMoveDetails);
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
                        setAllGodFavorsInGridPaneDisabled(gpP2GodFavors);

                        List<DiceDetails> playerOnTurnDice;
                        if (playerOneTurn) {
                            playerOnTurnDice = new ArrayList<>(playerOneAllDice);
                        } else {
                            playerOnTurnDice = new ArrayList<>(playerTwoAllDice);
                        }
                        List<DiceSymbols> symbolsPlayed = new ArrayList<>();
                        List<DiceSymbols> symbolsNotPlayed = new ArrayList<>();
                        for (var d : playerOnTurnDice) {
                            if (d.getIsChosenFromDiceTray()) {
                                symbolsPlayed.add(d.getDiceSymbols().get(0));
                            } else {
                                symbolsNotPlayed.add((d.getDiceSymbols().get(0)));
                            }
                        }
                        tempMoveDetails.setRoundNumber(roundCount);
                        tempMoveDetails.setTurnNumber(turnCount);
                        tempMoveDetails.setSymbolsPlayerOnTurnPlayed(symbolsPlayed);
                        tempMoveDetails.setSymbolsPlayerOnTurnDidntPlay(symbolsNotPlayed);
                        tempMoveDetails.setPlayer1CurrentCoins(playerOneCoinCount);
                        tempMoveDetails.setPlayer2CurrentCoins(playerTwoCoinCount);

                        playerMoves.add(tempMoveDetails);
                    } else { // ako nema u P2 trayu i nema u P1 trayu kada P2 stisne end Kraj runde ili igre
                        roundResolve();

                        newRoundAlert();

                        if (!playingIsDone) {
                            setupNewRound();
                        }
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

        playerMoves.add(tempMoveDetails);

        notChosenDice.clear();
        turnCount++;
    }

    private void setAllGodFavorsInGridPaneDisabled(GridPane gpArea) {
        gpArea.setDisable(true);
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
                d.getDiceButton().setVisible(false);
            }
            d.setCanBeSentToCenter(false);
        });
    }

    private void roundResolve() {
        btnRollDicePlayerOne.setVisible(false);
        btnRollDicePlayerTwo.setVisible(false);
        btnEndTurnPlayerOne.setVisible(false);
        btnEndTurnPlayerTwo.setVisible(false);
        setAllGodFavorsInGridPaneDisabled(gpP1GodFavors);
        setAllGodFavorsInGridPaneDisabled(gpP2GodFavors);

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

        adjustHealthPoolToDamageTaken();

        //endregion

        List<DiceDetails> playerOnTurnDice;
        if (playerOneTurn) {
            playerOnTurnDice = new ArrayList<>(playerOneAllDice);
        } else {
            playerOnTurnDice = new ArrayList<>(playerTwoAllDice);
        }
        List<DiceSymbols> symbolsPlayed = new ArrayList<>();
        List<DiceSymbols> symbolsNotPlayed = new ArrayList<>();
        for (var d : playerOnTurnDice) {
            if (d.getIsChosenFromDiceTray()) {
                symbolsPlayed.add(d.getDiceSymbols().get(0));
            } else {
                symbolsNotPlayed.add((d.getDiceSymbols().get(0)));
            }
        }
        tempMoveDetails.setSymbolsPlayerOnTurnPlayed(symbolsPlayed);
        tempMoveDetails.setSymbolsPlayerOnTurnDidntPlay(symbolsNotPlayed);
        tempMoveDetails.setPlayer1DamageTaken(playerOneTotalDamageTaken);
        tempMoveDetails.setPlayer2DamageTaken(playerTwoTotalDamageTaken);
        tempMoveDetails.setRoundOver(true);


        if (checkIfTheGameJustEnded(playerOneTotalDamageTaken, playerTwoTotalDamageTaken)) { // provjeri dal je neki od igrača pokupio 15 ili vise damage-a prije god powers
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

            adjustCoinCountToNewValues();
            //endregion

            tempMoveDetails.setPlayer1CurrentCoins(playerOneCoinCount);
            tempMoveDetails.setPlayer2CurrentCoins(playerTwoCoinCount);
            tempMoveDetails.setPlayer1GodFavorUsed("None");
            tempMoveDetails.setPlayer2GodFavorUsed("None");
            tempMoveDetails.setRoundOver(true);

            if (checkIfTheGameJustEnded(playerOneTotalDamageTaken, playerTwoTotalDamageTaken)) {
                recordWins(playerOneTotalDamageTaken, playerTwoTotalDamageTaken, LoginController.getPlayerOneDetails(), LoginController.getPlayerTwoDetails());
            }
        }
        playerMoves.add(tempMoveDetails);
    }

    private void adjustCoinCountToNewValues() {
        String p1Coins = Integer.toString(playerOneCoinCount);
        lblPlayerOneCoins.setText(p1Coins);

        String p2Coins = Integer.toString(playerTwoCoinCount);
        lblPlayerTwoCoins.setText(p2Coins);
    }

    private void adjustHealthPoolToDamageTaken() {
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
    }

    private void newRoundAlert() {
        if (!playingIsDone) {
            Alert newRoundAlert = new Alert(Alert.AlertType.INFORMATION);
            newRoundAlert.setTitle("ROUND END");
            newRoundAlert.setHeaderText(null);
            newRoundAlert.setContentText("Round " + (roundCount + 1) + "/6 is about to start!");
            newRoundAlert.showAndWait();
        }
    }

    private void setupNewRound() {
        roundCount++;
        if (roundCount % 2 == 0) { // ako je sljedeća runda parna igra prvi u rundi P2
            playerOneTurn = false;

            setupDiceForNewRound();

            btnRollDicePlayerOne.setVisible(false);
            btnRollDicePlayerTwo.setVisible(true);
        } else { // inače igra P1
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
        setAllGodFavorsInGridPaneDisabled(gpP1GodFavors);
        setAllGodFavorsInGridPaneDisabled(gpP2GodFavors);
        resetSymbolCounters();
        turnCount = 0;
    }

    private Boolean checkIfTheGameJustEnded(int playerOneTotalDamageTaken, int playerTwoTotalDamageTaken) {
        // game ends when one or both players die or when the round count reaches max
        return playerOneTotalDamageTaken >= 15 || playerTwoTotalDamageTaken >= 15 || roundCount == 6;
    }

    private void recordWins(int playerOneTotalDamageTaken, int playerTwoTotalDamageTaken, PlayerDetails player1, PlayerDetails player2) {
        if ((playerOneTotalDamageTaken >= 15 && playerTwoTotalDamageTaken >= 15) || (playerOneTotalDamageTaken < 15 && playerTwoTotalDamageTaken < 15)) { // Draw
            var tempDrawsP1 = Integer.valueOf(player1.getNumberOfDraws());
            tempDrawsP1++;
            player1.setNumberOfDraws(tempDrawsP1.toString());

            var tempDrawsP2 = Integer.valueOf(player2.getNumberOfDraws());
            tempDrawsP2++;
            player2.setNumberOfDraws(tempDrawsP2.toString());
        } else if (playerOneTotalDamageTaken >= 15) { // P2 wins
            var tempWinsP2 = Integer.valueOf(player2.getNumberOfWins());
            tempWinsP2++;
            player2.setNumberOfWins(tempWinsP2.toString());

            var tempLostP1 = Integer.valueOf(player1.getNumberOfLost());
            tempLostP1++;
            player1.setNumberOfLost(tempLostP1.toString());
        } else { // P1 wins
            var tempWinsP1 = Integer.valueOf(player1.getNumberOfWins());
            tempWinsP1++;
            player1.setNumberOfWins(tempWinsP1.toString());

            var tempLostP2 = Integer.valueOf(player2.getNumberOfLost());
            tempLostP2++;
            player2.setNumberOfLost(tempLostP2.toString());
        }


        List<DiceDetails> playerOnTurnDice = new ArrayList<>();
        if (playerOneTurn) {
            playerOnTurnDice = new ArrayList<>(playerOneAllDice);
        }
        List<DiceSymbols> symbolsPlayed = new ArrayList<>();
        List<DiceSymbols> symbolsNotPlayed = new ArrayList<>();
        for (var d : playerOnTurnDice) {
            if (d.getIsChosenFromDiceTray()) {
                symbolsPlayed.add(d.getDiceSymbols().get(0));
            } else {
                symbolsNotPlayed.add((d.getDiceSymbols().get(0)));
            }
        }
        String winnerOrDraw;
        if (playerOneTotalDamageTaken >= 15 && playerTwoTotalDamageTaken >= 15) { // Draw
            winnerOrDraw = "Draw";
        } else if (playerOneTotalDamageTaken >= 15) { // P2 wins
            winnerOrDraw = lblPlayerTwoName.getText();
        } else { // P1 wins
            winnerOrDraw = lblPlayerOneName.getText();
        }
        tempMoveDetails.setRoundNumber(roundCount);
        tempMoveDetails.setTurnNumber(turnCount);
        tempMoveDetails.setSymbolsPlayerOnTurnPlayed(symbolsPlayed);
        tempMoveDetails.setSymbolsPlayerOnTurnDidntPlay(symbolsNotPlayed);
        tempMoveDetails.setPlayer1DamageTaken(playerOneTotalDamageTaken);
        tempMoveDetails.setPlayer2DamageTaken(playerTwoTotalDamageTaken);
        tempMoveDetails.setPlayer1CurrentCoins(playerOneCoinCount);
        tempMoveDetails.setPlayer2CurrentCoins(playerTwoCoinCount);
        tempMoveDetails.setRoundOver(true);
        tempMoveDetails.setGameOver(true);
        tempMoveDetails.setWinnerNameOrDraw(winnerOrDraw);

        Alert startNewGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
        startNewGameAlert.setTitle("GAME OVER");
        startNewGameAlert.setHeaderText(null);
        startNewGameAlert.setContentText("Do you want to CONTINUE and start a NEW game?");
        Optional<ButtonType> result = startNewGameAlert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            Alert currentScoreAlert = new Alert(Alert.AlertType.INFORMATION);
            currentScoreAlert.setTitle("CURRENT SCORE");
            currentScoreAlert.setHeaderText(player1.getPlayerName() + " VS. " + player2.getPlayerName());
            currentScoreAlert.setContentText(player1.getNumberOfWins() + " : " + player2.getNumberOfWins());
            currentScoreAlert.showAndWait();

            playerMoves.clear();

            setupNewGame();
        } else {
            playingIsDone = true;

            playerMoves.add(tempMoveDetails);

            try {
                openResultsView();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
        turnCount = 0;
        playerOneTurn = true;
        btnRollDicePlayerOne.setVisible(true);
        btnRollDicePlayerTwo.setVisible(false);

        resetSymbolCounters();

        playerOneCoinCount = 0;
        playerTwoCoinCount = 0;
        lblPlayerOneCoins.setText("0");
        lblPlayerTwoCoins.setText("0");

        playerOneTotalDamageTaken = 0;
        playerTwoTotalDamageTaken = 0;

        gridPanePlayerOneHealth.getChildren().stream().toList().forEach(hp -> hp.setVisible(true));
        gridPanePlayerTwoHealth.getChildren().stream().toList().forEach(hp -> hp.setVisible(true));

        btnEndTurnPlayerOne.setVisible(false);
        btnEndTurnPlayerTwo.setVisible(false);

        setupDiceForNewRound();
        setAllGodFavorsInGridPaneDisabled(gpP1GodFavors);
        setAllGodFavorsInGridPaneDisabled(gpP2GodFavors);
    }

    private void FeatureNotYetImplementedAlert() {
        Alert featureNotImplemented = new Alert(Alert.AlertType.INFORMATION);
        featureNotImplemented.setTitle("WIP");
        featureNotImplemented.setHeaderText("WIP");
        featureNotImplemented.setContentText("Feature not yet implemented. WIP!");
        featureNotImplemented.showAndWait();
    }

    private void openResultsView() throws IOException {
        FxmlUtils.showScreen("resultsView.fxml", OrlogApplication.getMainStage(), 600, 400, "Results");
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


    public static List<MoveDetails> getPlayerMovesCollection() {
        return playerMoves;
    }


    @FXML
    public void clickOnGodFavor(ActionEvent actionEvent) {
        Button clickedGodFavorBtn = (Button) actionEvent.getSource();

        // if you don't have coins for a certain clicked favor return
        if (clickedGodFavorBtn.equals(btnThorP1) && playerOneCoinCount < 8) {
            notEnoughCoinAlert();
            return;
        } else if (clickedGodFavorBtn.equals(btnHelP1) && playerOneCoinCount < 12) {
            notEnoughCoinAlert();
            return;
        } else if (clickedGodFavorBtn.equals(btnIdunP1) && playerOneCoinCount < 10) {
            notEnoughCoinAlert();
            return;
        } else if (clickedGodFavorBtn.equals(btnThorP2) && playerTwoCoinCount < 8) {
            notEnoughCoinAlert();
            return;
        } else if (clickedGodFavorBtn.equals(btnHelP2) && playerTwoCoinCount < 12) {
            notEnoughCoinAlert();
            return;
        } else if (clickedGodFavorBtn.equals(btnIdunP2) && playerTwoCoinCount < 10) {
            notEnoughCoinAlert();
            return;
        }

        if (clickedGodFavorBtn.equals(btnThorP1)) {
            playerOneCoinCount -= 8;
            playerTwoTotalDamageTaken += 5;

            adjustCoinCountToNewValues();
            adjustHealthPoolToDamageTaken();

        } else if (clickedGodFavorBtn.equals(btnThorP2)) {
            playerTwoCoinCount -= 8;
            playerOneTotalDamageTaken += 5;

            adjustCoinCountToNewValues();
            adjustHealthPoolToDamageTaken();
        } else if (clickedGodFavorBtn.equals(btnHelP1)) {
            playerOneCoinCount -= 12;
            playerTwoTotalDamageTaken += 8;

            adjustCoinCountToNewValues();
            adjustHealthPoolToDamageTaken();
        } else if (clickedGodFavorBtn.equals(btnHelP2)) {
            playerTwoCoinCount -= 8;
            playerOneTotalDamageTaken += 8;

            adjustCoinCountToNewValues();
            adjustHealthPoolToDamageTaken();
        } else if (clickedGodFavorBtn.equals(btnIdunP1)) {
            playerOneCoinCount -= 10;
            var healAll = false;
            if (playerOneTotalDamageTaken <= 6) {
                playerOneTotalDamageTaken = 0;
                healAll = true;
            } else {
                playerOneTotalDamageTaken -= 6;
                healAll = false;
            }

            adjustCoinCountToNewValues();

            var p1HP = gridPanePlayerOneHealth.getChildren().stream().toList();
            healDamageTaken(playerOneTotalDamageTaken, p1HP, healAll);
        } else if (clickedGodFavorBtn.equals(btnIdunP2)) {
            playerTwoCoinCount -= 10;
            var healAll = false;
            if (playerTwoTotalDamageTaken <= 6) {
                playerTwoTotalDamageTaken = 0;
                healAll = true;
            } else {
                playerTwoTotalDamageTaken -= 6;
                healAll = false;
            }

            adjustCoinCountToNewValues();

            var p2HP = gridPanePlayerTwoHealth.getChildren().stream().toList();
            healDamageTaken(playerTwoTotalDamageTaken, p2HP, healAll);
        }

    }

    private void healDamageTaken(int damageTaken, List<Node> hpCollection, boolean healAll) {

        if (healAll) {
            for (var hp : hpCollection) {
                hp.setVisible(true);
            }
        } else {
            var skipHp = damageTaken;
            int i = 1;
            for (var hp : hpCollection) {
                if (i <= skipHp) {
                    hp.setVisible(false);
                } else {
                    hp.setVisible(true);
                }
                i++;
            }
        }
    }

    private void notEnoughCoinAlert() {
        Alert coinMissingAlert = new Alert(Alert.AlertType.INFORMATION);
        coinMissingAlert.setTitle("Coin missing!");
        coinMissingAlert.setHeaderText("Not enough coin to grant this favor!");
        coinMissingAlert.setContentText("Sacrifice more coin in the glory of the gods to ask them favors!");
        coinMissingAlert.showAndWait();
    }

    @FXML
    public void saveGame() throws IOException {
        List<SerializableDiceDetails> serializableDiceDetailsCollection = new ArrayList<>();
        List<Node> p1Buttons = gridP1AllDice.getChildren().stream().toList();
        List<Node> p2Buttons = gridP2AllDice.getChildren().stream().toList();

        saveDice(p1Buttons, playerOneAllDice, serializableDiceDetailsCollection);
        saveDice(p2Buttons, playerTwoAllDice, serializableDiceDetailsCollection);

        List<SerializableMatchData> serializableMatchDataCollection = new ArrayList<>();

        serializableMatchDataCollection.add(
                new SerializableMatchData(
                        playerOneTotalDamageTaken,
                        playerTwoTotalDamageTaken,
                        playerOneCoinCount,
                        playerTwoCoinCount,
                        turnCount,
                        roundCount,
                        playerOneTurn,
                        serializableDiceDetailsCollection
                )
        );

        try (ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream("savedMatchData.ser"))) {
            serializer.writeObject(serializableMatchDataCollection);
        }

        loadAllowed = true;
    }

    private void saveDice(List<Node> buttons, List<DiceDetails> playerDiceList, List<SerializableDiceDetails> serializableDiceDetailsCollection) {
        for (var b : buttons) {
            List<DiceSymbols> tempListSymbols = null;
            Boolean tempChosen = false;
            Boolean tempSentToCenter = false;
            for (var d : playerDiceList) {
                if (Objects.equals(d.getDiceButton().getId(), b.getId())) {
                    tempListSymbols = new ArrayList<>(d.getDiceSymbols());
                    tempChosen = d.getIsChosenFromDiceTray();
                    tempSentToCenter = d.getCanBeSentToCenter();
                }
            }

            serializableDiceDetailsCollection.add(
                    new SerializableDiceDetails(
                            new SerializableButton(b.getId(), tempListSymbols, b.isDisabled(), b.isVisible()),
                            tempListSymbols,
                            tempChosen,
                            tempSentToCenter
                    )
            );
        }
    }

    @FXML
    public void loadGame() throws IOException, ClassNotFoundException {

        if (!loadAllowed) {
            Alert loadDeniedAlert = new Alert(Alert.AlertType.WARNING);
            loadDeniedAlert.setTitle("Load denied");
            loadDeniedAlert.setHeaderText("You already loaded the game.");
            loadDeniedAlert.setContentText("Save the game again to load it.");
            loadDeniedAlert.showAndWait();

            return;
        }

        try (ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream("savedMatchData.ser"))) {
            List<SerializableMatchData> serializableMatchDataList = (List<SerializableMatchData>) deserializer.readObject();

            for (var m : serializableMatchDataList) {
                playerOneTotalDamageTaken = m.getP1DamageTaken();
                playerTwoTotalDamageTaken = m.getP2DamageTaken();
                adjustHealthPoolToDamageTaken();

                playerOneCoinCount = m.getP1CoinCount();
                playerTwoCoinCount = m.getP2CoinCount();
                adjustCoinCountToNewValues();

                turnCount = m.getTurnCount();
                roundCount = m.getRoundCount();

                playerOneTurn = m.getPlayerOneTurn();

                int i = 0;
                for (var d : m.getSerializableDiceDetailsCollection()) {
                    if (i < 8) {
                        loadButtonsAndChosenButtons(gridP1AllDice, d.getButton(), d.getChosenFromDiceTray(), hbPlayerOneChosenDice, d, playerOneAllDice);
                    } else {
                        loadButtonsAndChosenButtons(gridP2AllDice, d.getButton(), d.getChosenFromDiceTray(), hbPlayerTwoChosenDice, d, playerTwoAllDice);
                    }
                    i++;
                }

            }
        }

        if (playerOneTurn) {
            playerOneAllDice.forEach(d -> d.getDiceButton().setDisable(true));
        } else {
            playerTwoAllDice.forEach(d -> d.getDiceButton().setDisable(true));
        }

        loadAllowed = false;
    }

    private void loadButtonsAndChosenButtons(GridPane grid, SerializableButton btn, Boolean chosenFromDiceTray, HBox hbCentralContainer, SerializableDiceDetails diceDetails, List<DiceDetails> allDice) {

        for (var d : allDice) {
            if (Objects.equals(d.getDiceButton().getId(), btn.getBtnId())) {
                Button b = d.getDiceButton();

                b.setVisible(btn.getVisible());
                b.setDisable(btn.getDisabled());
                d.setIsChosenFromDiceTray(chosenFromDiceTray);
                d.setCanBeSentToCenter(diceDetails.getCanBeSentToCenter());

                List<DiceSymbols> symbols = btn.getDiceSymbols();
                String symbol = symbols.get(0).toString().trim();

                ImageView img = new ImageView(Objects.requireNonNull(getClass().getResource("/" + symbol + ".jpg")).toExternalForm());
                img.setFitHeight(60);
                img.setFitWidth(60);
                b.setPadding(new Insets(0, 0, 0, 0));
                b.setAccessibleText(symbol.trim());
                b.setGraphic(img);

                if (chosenFromDiceTray) {
                    img.setFitWidth(60);
                    img.setFitHeight(60);
                    hbCentralContainer.getChildren().add(
                            img
                    );
                }
            }
        }
    }

    @FXML
    public void createDocumentation() {
        File documentationFile = new File("documentation.html");

        try {
            FileWriter writer = new FileWriter(documentationFile);

            writer.write("<!DOCTYPE html>");
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>Project documentation</title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h1>Project documentation</h1>");
            writer.write("<p>Class list:</p>");

            List<Path> paths = Files.walk(Paths.get("."))
                    .filter(path -> path.getFileName().toString().endsWith(CLASS_EXTENSION))
                    .collect(Collectors.toList());

            for (Path path : paths) {
                String[] tokens = path.toString().split(Pattern.quote(System.getProperty("file.separator")));

                Boolean startBuildingPath = false;

                StringBuilder sb = new StringBuilder();

                for (String token : tokens) {
                    if ("classes".equals(token)) {
                        startBuildingPath = true;
                        continue;
                    }

                    if (startBuildingPath) {
                        if (token.endsWith(CLASS_EXTENSION)) {
                            sb.append(token.substring(0, token.indexOf(".")));
                        } else {
                            sb.append(token);
                            sb.append(".");
                        }
                    } else {
                        continue;
                    }
                }

                if ("module-info".equals(sb.toString())) {
                    continue;
                }

                System.out.println("Fully qualified name: " + sb.toString());

                try {
                    Class<?> clazz = Class.forName(sb.toString());

                    writer.write("<h2>" + Modifier.toString(clazz.getModifiers()) + " " + clazz.getName() + "</h2>");

                    StringBuilder classFieldString = new StringBuilder();

                    for (Field classField : clazz.getDeclaredFields()) {
                        Annotation[] annotations = classField.getAnnotations();
                        if (annotations.length != 0) {
                            for (Annotation a : annotations) {
                                classFieldString.append(a.toString());
                                classFieldString.append("<br />");
                            }
                        }
                        classFieldString.append(Modifier.toString(classField.getModifiers()));
                        classFieldString.append(" ");
                        classFieldString.append(classField.getType().getSimpleName());
                        classFieldString.append(" ");
                        classFieldString.append(classField.getName());
                        classFieldString.append(" ");
                        classFieldString.append("<br /><br />");
                    }

                    writer.write("<h3>Fields</h3>");

                    writer.write("<h4>" + classFieldString + "</h4>");

                    Constructor[] constructors = clazz.getConstructors();

                    writer.write("<h3>Constructors:</h3>");

                    for (Constructor c : constructors) {
                        StringBuilder cb = new StringBuilder();

                        Annotation[] annotations = c.getAnnotations();
                        if (annotations.length != 0) {
                            for (Annotation a : annotations) {
                                cb.append(a.toString());
                                cb.append("<br />");
                            }
                        }

                        String constructorParams = generateDocumentation(c);

                        writer.write("<h4>" + cb.toString() + "Constructor:" + Modifier.toString(c.getModifiers()) + " " + c.getName()
                                + "(" + constructorParams + ")" + "</h4>");
                    }

                    Method[] methods = clazz.getMethods();

                    writer.write("<h3>Methods:</h3>");

                    for (Method m : methods) {
                        StringBuilder mb = new StringBuilder();

                        Annotation[] annotations = m.getAnnotations();
                        if (annotations.length != 0) {
                            for (Annotation a : annotations) {
                                mb.append(a.toString());
                                mb.append("<br />");
                            }
                        }


                        String methodsParams = generateDocumentation(m);

                        StringBuilder exceptionsBuilder = new StringBuilder();

                        for (int i = 0; i < m.getExceptionTypes().length; i++) {
                            if (exceptionsBuilder.isEmpty()) {
                                exceptionsBuilder.append(" throws ");
                            }

                            Class exceptionClass = m.getExceptionTypes()[i];
                            exceptionsBuilder.append(exceptionClass.getSimpleName());

                            if (i < m.getExceptionTypes().length - 1) {
                                exceptionsBuilder.append(", ");
                            }
                        }

                        writer.write("<h4>" + mb.toString() + "Method:" + Modifier.toString(m.getModifiers())
                                + " " + m.getReturnType().getSimpleName()
                                + " " + m.getName() + "(" + methodsParams + ")"
                                + " " + exceptionsBuilder.toString()
                                + "</h4>");
                    }

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            writer.write("</body>");
            writer.write("</html>");
            writer.close();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while generating documentation!");
            alert.setHeaderText("Cannot find the files");
            alert.setContentText("The class files cannot be accessed.");

            alert.showAndWait();
        }
    }

    private <T extends Executable> String generateDocumentation(T executable) {
        Parameter[] params = executable.getParameters();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.length; i++) {
            String modifierString = Modifier.toString(params[i].getModifiers());

            if (!modifierString.isEmpty()) {
                sb.append(modifierString);
                sb.append(" ");
            }
            sb.append(params[i].getType().getSimpleName());
            sb.append(" ");
            sb.append(params[i].getName());

            if (i < (params.length - 1)) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
