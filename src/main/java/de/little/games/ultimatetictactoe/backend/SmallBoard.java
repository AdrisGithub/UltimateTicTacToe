package de.little.games.ultimatetictactoe.backend;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SmallBoard {
    private final Cell[][] miniBoard;
    private char winner;
    private final GridPane pane;
    private final ImageView imageView;
    public SmallBoard(GridPane pane,ImageView imageView){
        this.imageView = imageView;
        this.pane = pane;
        this.winner = ' ';
        this.miniBoard = new Cell[3][3];
        initMiniBoard();
    }

    private void initMiniBoard() {
        for (int x=0;x< miniBoard.length;x++){
            for (int y=0;y<miniBoard.length;y++){
                for (int i=y+x;i<pane.getChildren().size();i++){
                    if(pane.getChildren().get(i) instanceof ImageView && pane.getChildren().get(i).getId().equals(y+""+x)){
                        miniBoard[y][x] = new Cell((ImageView) pane.getChildren().get(i));
                        break;
                    }
                }
            }
        }
    }

    public boolean lockCell(int xPos,int yPos,char Player){
        yPos%=3;xPos%=3;
        if (miniBoard[xPos][yPos].IsSet()) return false;
        miniBoard[xPos][yPos].setOwner(Player);
        return true;
    }
    public char hasAWinner(){
        //Todo Code schreiben der schaut ob da ein gewinner drin ist wenn ja welcher
        return ' ';
    }
    public void changeOpacity(double val){
        imageView.setOpacity(val);
    }

}
