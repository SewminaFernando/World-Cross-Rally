package code.programmingcw_test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DriversListPageController implements Initializable {

    @FXML
    private TableColumn<Driver, Integer> age;

    @FXML
    private TableColumn<Driver, String> carModel;

    @FXML
    private TableColumn<Driver, Integer> currentPoints;

    @FXML
    private TableColumn<Driver, String> fullName;

    @FXML
    private TableColumn<Driver, Integer> rank;
    @FXML
    private TableColumn<Driver, Integer> teamName;
    @FXML
    private TableView<Driver> standingTable;

    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        new MainController().navigateToMenu(event);
    }

    @FXML
    void editDrivers(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("drivers-page.fxml"));
        stage.setScene(new Scene(root, 1200, 832));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the table columns

        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        carModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        teamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        currentPoints.setCellValueFactory(new PropertyValueFactory<>("currentPoints"));

        FileHandler fileHandler = new FileHandler();
        fileHandler.loadDriversFromCSV();

        ObservableList<Driver> drivers = FXCollections.observableArrayList(new DriverList().sortByScore());

        standingTable.setItems(drivers);
    }
}
