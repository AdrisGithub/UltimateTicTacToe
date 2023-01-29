package de.little.games.ultimatetictactoe.backend;

import de.little.games.ultimatetictactoe.frontend.TicTacToeApplication;
import javafx.scene.image.Image;
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
    public boolean hasAWinner(char player){
        if(IsSet())return true;
        if(checkRows()||checkColumns()||checkDiagonal()){
            setWinner(player);
            return true;
        }
        return false;
    }
    public boolean IsSet(){
        return winner != ' ';
    }

    public char getOwner() {
        return winner;
    }

    private boolean checkDiagonal() {
        if(miniBoard[0][0].getOwner()== miniBoard[1][1].getOwner() &&miniBoard[1][1].getOwner()==miniBoard[2][2].getOwner()&& miniBoard[2][2].IsSet()) return true;
        if(miniBoard[0][2].getOwner()== miniBoard[1][1].getOwner() &&miniBoard[1][1].getOwner()==miniBoard[2][0].getOwner()&& miniBoard[2][0].IsSet()) return true;
        return false;
    }

    private boolean checkColumns() {
        for (int i=0;i<miniBoard.length;i++){
            if(miniBoard[1][i].getOwner()== miniBoard[2][i].getOwner() && miniBoard[2][i].getOwner()==miniBoard[0][i].getOwner() && miniBoard[0][i].IsSet()) return true;
        }
        return false;
    }

    private boolean checkRows() {
        for (Cell[] cells : miniBoard) {
            if (cells[1].getOwner() == cells[2].getOwner() && cells[2].getOwner() == cells[0].getOwner()&& cells[0].IsSet()) return true;
        }
        return false;
    }

    public void changeOpacity(double val){
        imageView.setOpacity(val);
    }

    public boolean isFull() {
        for (Cell[] cells: miniBoard){
            for (Cell cell:cells){
                if (!cell.IsSet()) return false;
            }
        }
        return true;
    }
    public void setWinner(char o){
        this.winner = o;
        this.imageView.setImage(new Image(TicTacToeApplication.toAbsolutePath("src/main/resources/de/little/games/ultimatetictactoe/images/"+o+".png")));
    }
}
