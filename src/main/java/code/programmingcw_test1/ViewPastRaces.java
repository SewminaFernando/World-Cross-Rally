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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewPastRaces implements Initializable {

    @FXML
    private TableColumn<Player, LocalDate> dateColumn;

    @FXML
    private TableColumn<Player, String> locationColumn;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, Integer> pointsColumn;

    @FXML
    private TableColumn<Player, Integer> rankColumn;

    @FXML
    private TableView<Player> standingTable;

    @FXML
    private TableColumn<Driver, String> teamColumn;

    public void initialize(URL url, ResourceBundle resource) {

        // Set up the table columns
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("currentPoints"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<Player> players = FXCollections.observableArrayList(new PlayersList().sortByDate());
        standingTable.setItems(players);
    }

    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        new MainController().navigateToMenu(event);
    }

    @FXML
    private void simulateRandomRace(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("simulate-random-race.fxml"));
        stage.setScene(new Scene(root, 1200, 797));
    }

}
