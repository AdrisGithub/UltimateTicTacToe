package de.little.games.ultimatetictactoe.backend;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BigBoard {
    private final SmallBoard[][] bigBoard;
    private char winner;
    private char currentPlayer;
    private final GridPane parent;
    private final int[] nextBoard;
    public BigBoard(GridPane parent){
        nextBoard = new int[2];
        initNextBoard();
        currentPlayer = 'O';
        this.parent = parent;
        this.bigBoard = new SmallBoard[3][3];
        this.winner = ' ';
        this.initBigBoard();
    }

    private void initNextBoard() {
        nextBoard[0] = 3;
        nextBoard[1] = 3;
    }

    private void initBigBoard() {
        for (int x=0;x< bigBoard.length;x++){
            for (int y=0;y<bigBoard.length;y++){
                for (int i=y+x;i<parent.getChildren().size();i++){
                    if(parent.getChildren().get(x+y) instanceof GridPane && parent.getChildren().get(i).getId().equals(y+""+x)){
                        for (int o=i+1;o<parent.getChildren().size();o++) {
                            if(parent.getChildren().get(o).getId().equals(y+""+x)){
                                bigBoard[y][x] = new SmallBoard((GridPane) parent.getChildren().get(i), (ImageView) parent.getChildren().get(o));
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    public boolean isValid(int xPos,int yPos,char Player){
        if((xPos/3==nextBoard[0]&&yPos/3==nextBoard[1])||nextBoard[0]==3){
            return bigBoard[xPos/3][yPos/3].lockCell(xPos, yPos, Player);
        }
        return false;
    }

    public void registerMouseClick(int xPos,int yPos) {
        if(winner==' ' && isValid(xPos,yPos,currentPlayer)){
            if(bigBoard[xPos/3][yPos/3].hasAWinner(currentPlayer)){
                if(isGameOver(currentPlayer)) return;
            }
            nextBoard(xPos,yPos);
            switchPlayer();
        }
    }

    private boolean isGameOver(char currentPlayer) {
        if(checkColumns()||checkRows()||checkDiagonal()){
            winner = currentPlayer;
            resetOpacity(1.0);
            return true;
        }
        return false;
    }
    private boolean checkDiagonal() {
        if(bigBoard[0][0].getOwner()== bigBoard[1][1].getOwner() &&bigBoard[1][1].getOwner()==bigBoard[2][2].getOwner()&& bigBoard[2][2].IsSet()) return true;
        if(bigBoard[0][2].getOwner()== bigBoard[1][1].getOwner() &&bigBoard[1][1].getOwner()==bigBoard[2][0].getOwner()&& bigBoard[2][0].IsSet()) return true;
        return false;
    }

    private boolean checkColumns() {
        for (int i=0;i<bigBoard.length;i++){
            if(bigBoard[1][i].getOwner()== bigBoard[2][i].getOwner() && bigBoard[2][i].getOwner()==bigBoard[0][i].getOwner() && bigBoard[0][i].IsSet()) return true;
        }
        return false;
    }

    private boolean checkRows() {
        for (SmallBoard[] cells : bigBoard) {
            if (cells[1].getOwner() == cells[2].getOwner() && cells[2].getOwner() == cells[0].getOwner()&& cells[0].IsSet()) return true;
        }
        return false;
    }

    private void nextBoard(int xPos,int yPos){
        nextBoard[0]= (xPos%3);
        nextBoard[1]= (yPos%3);
        if(!isFull(nextBoard)){
            resetOpacity(0.6);
            bigBoard[nextBoard[0]][nextBoard[1]].changeOpacity(0.1);
        }else{
            resetOpacity(0.1);
            nextBoard[0] = 3;
            nextBoard[1] = 3;
        }
    }

    private boolean isFull(int[] nextBoard) {
        return bigBoard[nextBoard[0]][nextBoard[1]].isFull();
    }

    private void resetOpacity(double val) {
        for (SmallBoard[] rows:bigBoard) {
            for (SmallBoard square:rows) {
                square.changeOpacity(val);
            }
        }
    }

    private void switchPlayer(){
        currentPlayer = currentPlayer == 'O' ? 'X' : 'O';
    }
}
