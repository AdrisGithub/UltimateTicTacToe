module de.little.games.ultimatetictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.little.games.ultimatetictactoe to javafx.fxml;
    exports de.little.games.ultimatetictactoe.backend;
    exports de.little.games.ultimatetictactoe.frontend;
    opens de.little.games.ultimatetictactoe.frontend to javafx.fxml;
}