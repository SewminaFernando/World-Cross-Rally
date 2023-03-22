package code.programmingcw_test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class WorldCrossRallyChampionship extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WorldCrossRallyChampionship.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setScene(scene);
        stage.show();

        // stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeEvent);
        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeEvent);

    }

    private void closeEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Hello world");
        Optional<ButtonType> res = alert.showAndWait();

        // application logic

        if (res.isPresent()){
            if (res.get().equals(ButtonType.CANCEL)) {
                event.consume();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}