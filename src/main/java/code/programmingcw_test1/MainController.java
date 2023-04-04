package code.programmingcw_test1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {


    @FXML
    void goToMenu(MouseEvent event) throws IOException {
        FileHandler handler = new FileHandler();
        handler.loadPlayersFromCSV();

        navigateToMenu(event);
    }

    public void navigateToMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setScene(new Scene(root, 1200 , 797));
    }
}