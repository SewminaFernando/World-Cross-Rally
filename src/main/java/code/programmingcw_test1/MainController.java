package code.programmingcw_test1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Optional;

public class MainController {


    //Close the Stage
    @FXML
    void close(MouseEvent windowClose) {
        Stage stage = (Stage) ((Button)windowClose.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void goToMenu(MouseEvent event) throws IOException {
        // Create the confirmation dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Data Retrieving");
        alert.setHeaderText("Do you want to retrieve previous data?");
        alert.setContentText("You can retrieve data about drivers and previous races");

        // Add OK and Cancel buttons
        ButtonType buttonTypeOK = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Show the dialog and wait for a response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK){
            System.out.println("Ela");
        } else {
            System.out.println("Bla");
        }

        navigateToMenu(event);
    }

    public void navigateToMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setScene(new Scene(root, 900 , 600));
    }
}