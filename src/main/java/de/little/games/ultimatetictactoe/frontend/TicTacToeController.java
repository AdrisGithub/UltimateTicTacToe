package de.little.games.ultimatetictactoe.frontend;

import de.little.games.ultimatetictactoe.backend.BigBoard;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {

    public GridPane MainPane;
    private BigBoard gameBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard = new BigBoard(MainPane);
        MainPane.setOnMouseClicked(mouseEvent -> gameBoard.registerMouseClick((int)mouseEvent.getX()/100,(int)mouseEvent.getY()/100));
    }
}