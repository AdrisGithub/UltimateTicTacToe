package de.little.games.ultimatetictactoe.backend;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BigBoard {
    private final SmallBoard[][] bigBoard;
    private char winner;
    private char currentPlayer;
    private final GridPane parent;
    public BigBoard(GridPane parent){
        currentPlayer = 'O';
        this.parent = parent;
        this.bigBoard = new SmallBoard[3][3];
        this.winner = ' ';
        this.initBigBoard();
    }

    private void initBigBoard() {
        for (int x=0;x< bigBoard.length;x++){
            for (int y=0;y<bigBoard.length;y++){
                for (int i=y+x;i<parent.getChildren().size();i++){
                    if(parent.getChildren().get(x+y) instanceof GridPane && parent.getChildren().get(i).getId().equals(y+""+x)){
                        bigBoard[y][x] = new SmallBoard((GridPane) parent.getChildren().get(i),(ImageView) parent.getChildren().get(i+9));
                        break;
                    }
                }
            }
        }
    }
    public boolean isValid(int xPos,int yPos,char Player){
        return bigBoard[xPos/3][yPos/3].lockCell(xPos, yPos, Player);
    }

    public void registerMouseClick(int xPos,int yPos) {
        if(isValid(xPos,yPos,currentPlayer)){
            switchPlayer();
        }
    }
    private void switchPlayer(){
        currentPlayer = currentPlayer == 'O' ? 'X' : 'O';
    }
}
