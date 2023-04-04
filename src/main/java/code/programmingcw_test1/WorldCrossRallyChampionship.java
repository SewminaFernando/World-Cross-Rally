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

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WorldCrossRallyChampionship.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 797);
        stage.setScene(scene);
        stage.show();

        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeEvent);

    }

    private void closeEvent(WindowEvent event) {
        // Create a new Alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Set the title and content text
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
                FileHandler.saveDriversToCSV();
                FileHandler.saveRaceDetailsToCSV();
            } else if (response == cancelButton) {
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}