package de.little.games.ultimatetictactoe.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:/"+toAbsolutePath("src/main/resources/de/little/games/ultimatetictactoe/tictactoe.fxml")));
        Scene scene = new Scene(fxmlLoader.load(), 900,900);
        stage.setTitle("TicTacToe");
        stage.setResizable(false);
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/de/little/games/ultimatetictactoe/images/Logo.jpg")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static String toAbsolutePath(String maybeRelative) {
        Path path = Paths.get(maybeRelative);
        if (!path.isAbsolute()) {
            Path base = Paths.get("");
            path = base.resolve(path).toAbsolutePath();
        }
        return path.normalize().toString();
    }

}