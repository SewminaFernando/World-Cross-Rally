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


    // Go to the menu stage when button is clicked
    @FXML
    private void goToMenu(MouseEvent event) throws IOException {
        // Loading previous races to the program
        RaceCSVHandler raceHandler = new RaceCSVHandler("src/main/java/csvfiles/PastRandomRaces.csv");
        raceHandler.loadData();

        navigateToMenu(event);
    }

    /**
     * This method navigates to the Menu of the program
     * @param event the mouse Event
     * @throws IOException If an I/O error occurs
     */
    public void navigateToMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setScene(new Scene(root, 1200 , 797));
    }
}