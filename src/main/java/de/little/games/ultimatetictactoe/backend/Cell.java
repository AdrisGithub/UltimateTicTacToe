package de.little.games.ultimatetictactoe.backend;

import de.little.games.ultimatetictactoe.frontend.TicTacToeApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell {
    private ImageView image;
    private char owner;
    public Cell(ImageView image){
        this.image = image;
        this.owner = ' ';
    }
    public char getOwner() {
        return this.owner;
    }
    public boolean IsSet(){
        return this.owner != ' ';
    }
    public void setOwner(char o){
        this.owner = o;
        this.image.setImage(new Image(TicTacToeApplication.toAbsolutePath("src/main/resources/de/little/games/ultimatetictactoe/images/"+o+".png")));
    }

}
