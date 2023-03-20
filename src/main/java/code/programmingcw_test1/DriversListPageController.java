package code.programmingcw_test1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DriversListPageController {
    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        new MainController().navigateToMenu(event);
    }

    @FXML
    void editDrivers(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("drivers-page.fxml"));
        stage.setScene(new Scene(root, 1200, 832));

        new DraggableStage().dragStage(root.getScene(), stage);
    }
}
