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
import java.util.*;

import static java.lang.Integer.parseInt;

public class SimulateRandomRaceController implements Initializable {
    // FXML Elements
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

    // Initializes the TableView with the appropriate columns and data.
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        // Set up the table columns
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("currentPoints"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Display the number of drivers which are in the program
        noOfDrivers.setText(Integer.toString(DriverList.driverList.size()));

        // make user can pick the date from the calendar only
        date.setEditable(false);
    }

    // Handle the back to menu button click
    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        new MainController().navigateToMenu(event);
    }

    // Handle the view past races button click
    @FXML
    private void viewPastRaces(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view-past-races.fxml"));
        stage.setScene(new Scene(root, 1200, 797));
    }


    // Generate random race
    @FXML
    private void generateRandomRace(MouseEvent event) {
        // Get the number of competitors and the selected date from the input fields
        String noOfCompetitors = numberOfCandidates.getText();
        LocalDate selectedDate = date.getValue();
        try {
            // Check the fields have been filled
            if (DataValidation.haveEmptyTextFields(noOfCompetitors,selectedDate)) {
                throw new Exception("Please fill all the fields.");
            }
            int noOfCandidate = parseInt(noOfCompetitors);

            // Check if there are enough drivers in the program to generate a race
            if (noOfCandidate > DriverList.driverList.size()) {
                throw new Exception("Don't have enough drivers in the program");
            }
            // Check if the user has entered at least 3 drivers
            if (noOfCandidate<3){
                throw new Exception("You should enter 3 or more drivers to Generate the race.");
            }

            // Choose a random location from the list
            List<String> raceLocations = Arrays.asList("Nyirád", "Höljes", "Montalegre", "Barcelona", "Rīga", "Norway");
            Random random = new Random();
            String location = raceLocations.get(random.nextInt(raceLocations.size()));

            // Shuffle the list of drivers and select the top given number of drivers (noOfCandidate).
            Collections.shuffle(DriverList.driverList);
            List<Driver> candidateList = DriverList.driverList.subList(0, noOfCandidate);

            // Create a new ObservableList of players to hold the results
            ObservableList<Player> players = FXCollections.observableArrayList();

            // Declaration of variables
            String fullName;
            String teamName;
            int rank;
            Player player;

            // Loop through the selected drivers and calculate their rankings and points
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

                // Create a new player object and add it to the list of players
                player = new Player(selectedDate, location, rank, fullName, teamName, candidateList.get(i).currentPoints);
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

    // Clear the all text fields
    @FXML
    private void clearFields(MouseEvent event){
        numberOfCandidates.clear();
        date.setValue(null);
    }

    // Handle the data retrieving from the file when button clicked
    @FXML
    private void retrieveData(MouseEvent event){
        DriverCSVHandler driverCSVHandler = new DriverCSVHandler("src/main/java/csvfiles/Drivers.csv");
        driverCSVHandler.loadData();
        noOfDrivers.setText(Integer.toString(DriverList.driverList.size()));
    }

    /**
     * Fades out the error message label over a duration of 5 seconds.
     */
    private void fadeErrorMessage(){
        // Create a fade transition animation
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), displayMsg);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(1);
        // Start the transition
        fadeTransition.play();
    }
}
