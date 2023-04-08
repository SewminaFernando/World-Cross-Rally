package code.programmingcw_test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class WorldCrossRallyChampionship extends Application {

    // Starts the application by loading the main.fxml file and creating the main scene
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(WorldCrossRallyChampionship.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 797);
        stage.setTitle("World RX Championship");
        // Set the scene as the primary scene for the stage
        stage.setScene(scene);
        stage.show();

        // Add an event filter to the primary stage to handle the window close request
        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeEvent);

    }

    // Handle the close button
    private void closeEvent(WindowEvent event) {
        // Create a new Alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Set the title, header text and content text
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save your changes?");
        alert.setContentText("When saving data your CSV Files be overwritten.");

        // Add the Save, Don't Save, and Cancel buttons
        ButtonType saveButton = new ButtonType("Save");
        ButtonType dontSaveButton = new ButtonType("Don't Save");
        ButtonType cancelButton = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);

        // Show the alert and wait for the user to make a selection
        alert.showAndWait().ifPresent(response -> {
            if (response == saveButton) {
                DriverCSVHandler driverHandler = new DriverCSVHandler("src/main/java/csvfiles/Drivers.csv");
                driverHandler.saveData();
                RaceCSVHandler raceHandler = new RaceCSVHandler("src/main/java/csvfiles/PastRandomRaces.csv");
                raceHandler.saveData();
            } else if (response == cancelButton) {
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}