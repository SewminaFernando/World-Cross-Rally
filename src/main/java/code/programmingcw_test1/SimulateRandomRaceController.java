package code.programmingcw_test1;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.Integer.parseInt;

public class SimulateRandomRaceController implements Initializable {
    @FXML
    private DatePicker date;

    @FXML
    private TableColumn<Player, LocalDate> dateColumn;

    @FXML
    private TableColumn<Player, String> locationColumn;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private Text noOfDrivers;

    @FXML
    private TextField numberOfCandidates;

    @FXML
    private TableColumn<Player, Integer> pointsColumn;

    @FXML
    private TableColumn<Player, Integer> rankColumn;

    @FXML
    private TableView<Player> standingTable;

    @FXML
    private TableColumn<Driver, String> teamColumn;

    @FXML
    private Label displayMsg;


    @Override
    public void initialize(URL url, ResourceBundle resource) {
        // Set up the table columns
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("currentPoints"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        noOfDrivers.setText(Integer.toString(DriverList.driverList.size()));

        date.setEditable(false);
    }

    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        new MainController().navigateToMenu(event);
    }

    @FXML
    private void viewPastRaces(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view-past-races.fxml"));
        stage.setScene(new Scene(root, 1200, 797));
    }


    @FXML
    private void generateRandomRace(MouseEvent event) {
        try {
            if (!(isFilled())) {
                throw new Exception("Please fill all the fields.");
            }
            int noOfCandidate = parseInt(numberOfCandidates.getText());

            if (noOfCandidate > DriverList.driverList.size()) {
                throw new Exception("Don't have enough drivers in the program");
            }
            if (noOfCandidate<3){
                throw new Exception("You should enter 3 or more drivers to Generate the race.");
            }
            LocalDate selectedDate = date.getValue();
            LocalDate fixedDate = LocalDate.of(2022, 1, 1);

            int daysBetween = (int) ChronoUnit.DAYS.between(fixedDate,selectedDate);

            List<String> raceLocations = Arrays.asList("Nyirád", "Höljes", "Montalegre", "Barcelona", "Rīga", "Norway");
            Random random = new Random();
            String location = raceLocations.get(random.nextInt(raceLocations.size()));


            Collections.shuffle(DriverList.driverList);
            List<Driver> candidateList = DriverList.driverList.subList(0, noOfCandidate);

            ObservableList<Player> players = FXCollections.observableArrayList();

            String fullName;
            String teamName;
            int rank;
            Player player;

            for (int i = 0; i < candidateList.size(); i++) {

                fullName = candidateList.get(i).fullName;
                teamName = candidateList.get(i).teamName;

                rank = i + 1;

                if (rank == 1)
                    candidateList.get(i).currentPoints += 10;
                else if (rank == 2)
                    candidateList.get(i).currentPoints += 7;
                else if (rank == 3)
                    candidateList.get(i).currentPoints += 5;

                player = new Player(selectedDate, location, rank, fullName, teamName, candidateList.get(i).currentPoints,daysBetween);
                PlayersList.player.add(player);
                players.add(player);
            }
            displayMsg.setTextFill(Color.GREEN);
            displayMsg.setText("See the Results.....");
            standingTable.setItems(players);
            clearFields(event);
        }catch (NumberFormatException exception){
            displayMsg.setText("Invalid Input");
        }catch (Exception e){
            displayMsg.setText(e.getMessage());
        }
        fadeErrorMessage();
    }

    @FXML
    private void clearFields(MouseEvent event){
        numberOfCandidates.clear();
        date.setValue(null);
    }

    @FXML
    private void retrieveData(MouseEvent event){
        FileHandler.loadDriversFromCSV();
        noOfDrivers.setText(Integer.toString(DriverList.driverList.size()));
    }

    private boolean isFilled(){
        if (numberOfCandidates.getText().isEmpty() || date.getValue() == null){
            return false;
        }
        return true;
    }

    private void fadeErrorMessage(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), displayMsg);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
    }
}
