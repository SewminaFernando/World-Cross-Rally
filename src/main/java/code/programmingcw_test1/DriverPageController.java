package code.programmingcw_test1;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class DriverPageController implements Initializable{

    //FXML Elements
    @FXML
    private TableView<Driver> driversTable;

    @FXML
    private TableColumn<Driver, Integer> age;

    @FXML
    private TableColumn<Driver, String> carModel;

    @FXML
    private TableColumn<Driver, Integer> currentPoints;

    @FXML
    private TableColumn<Driver, String> fullName;

    @FXML
    private TableColumn<Driver, String> teamName;

    @FXML
    private TextField ageField,carModelField,currentPointsField,teamNameField,fullNameField,searchField;

    @FXML
    private Label errorMsg;

    // Initializes the TableView with the appropriate columns and data.
    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Set up the table columns
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        carModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        teamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        currentPoints.setCellValueFactory(new PropertyValueFactory<>("currentPoints"));

        // Create an ObservableList of drivers sorted by score
        ObservableList<Driver> drivers = FXCollections.observableArrayList(new DriverList().sortByScore());
        // Set the ObservableList as the items for the TableView
        driversTable.setItems(drivers);

        //Filling the Text Fields when the row is clicked in the table
        driversTable.setOnMouseClicked(event -> {
            if (event.getClickCount()==1){
                Driver driver = driversTable.getSelectionModel().getSelectedItem();
                if (driver != null){
                    // Setting data to the text fields
                    fullNameField.setText(driver.getFullName());
                    ageField.setText(Integer.toString(driver.getAge()));
                    carModelField.setText(driver.getCarModel());
                    teamNameField.setText(driver.getTeamName());
                    currentPointsField.setText(Integer.toString(driver.getCurrentPoints()));
                }
            }
            // Unselect the row when double-clicked the selected row
            if (event.getClickCount() == 2 && driversTable.getSelectionModel().getSelectedItem() != null) {
                driversTable.getSelectionModel().clearSelection();
                clearFields();
            }
        });
    }

    // Add drivers to the driver list if user inputs are valid
    @FXML
    private void addDriver(MouseEvent event) {
        // Get data from the text fields
        String age = ageField.getText();
        String points = currentPointsField.getText();
        String fullName = fullNameField.getText();
        String carModel = carModelField.getText();
        String teamName = teamNameField.getText();
        try {
            // Check if all fields are filled
            if (DataValidation.haveEmptyTextFields(age,points,fullName,carModel,teamName)) {
                throw new Exception("Please fill all the fields.");
            }
            // Check if the driver already exists
            if (isDuplicateDriver()) {
                throw new Exception("The driver is already in the program.");
            }
            int ageInt = parseInt(age);
            // Check if age is in Valid Range
            if (DataValidation.ageIsNotValid(ageInt)) {
                throw new Exception("Age must be between 18 and 50.");
            }
            int currentPoints = parseInt(points);
            // Check if current points are not negative
            if (DataValidation.pointsAreNotValid(currentPoints)) {
                throw new Exception("Current points cannot be negative.");
            }
            errorMsg.setTextFill(Color.GREEN);
            errorMsg.setText("You Successfully added a driver.");
            // Create and add the new driver to the driverList
            Driver newDriver = new Driver(fullName, ageInt, carModel, teamName, currentPoints);
            DriverList.driverList.add(newDriver);
            reloadTable();
            clearFields();
        }catch (NumberFormatException exception){
            //Display the error message
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText("Invalid input. Age and Current Points\nmust be Integers.");
        } catch (Exception e) {
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText(e.getMessage());
        }
        fadeErrorMessage();
    }

    @FXML
    private void deleteDriver(MouseEvent event) {
        // get the selected item from the table view
        Driver selectedItem = driversTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            // if no item is selected, display error message and return
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText("There is not a selected driver.");
            fadeErrorMessage();
            return;
        }
        // get the cell data for each column of the selected item
        String deleteName = fullName.getCellData(selectedItem);
        int deleteAge = age.getCellData(selectedItem);
        String deleteCar = carModel.getCellData(selectedItem);
        String deleteTeam = teamName.getCellData(selectedItem);
        int deletedPoints = currentPoints.getCellData(selectedItem);

        // remove the selected driver from the driver list
        for (int i = 0; i < DriverList.driverList.size(); i++) {
            if (Objects.equals(DriverList.driverList.get(i).fullName, deleteName) &&
                    Objects.equals(DriverList.driverList.get(i).age, deleteAge) &&
                    Objects.equals(DriverList.driverList.get(i).carModel, deleteCar) &&
                    Objects.equals(DriverList.driverList.get(i).teamName, deleteTeam) &&
                    Objects.equals(DriverList.driverList.get(i).currentPoints, deletedPoints)) {
                DriverList.driverList.remove(i);
                break;
            }
        }
        // display success message and update table view
        errorMsg.setTextFill(Color.GREEN);
        errorMsg.setText("You Successfully deleted a driver.");
        fadeErrorMessage();
        reloadTable();
        clearFields();
    }

    // Update the Driver Details
    @FXML
    private void updateDriver(MouseEvent event) {
        // Get data from the text fields.
        String age = ageField.getText();
        String points = currentPointsField.getText();
        String fullName = fullNameField.getText();
        String carModel = carModelField.getText();
        String teamName = teamNameField.getText();

        try {
            // Check if all fields are filled
            if (DataValidation.haveEmptyTextFields(age,points,fullName,carModel,teamName)) {
                throw new Exception("Please fill all the fields.");
            } else {
                int ageInt = parseInt(age);
                int currentPoints = parseInt(points);
                Driver updatedDriver = new Driver(fullName, ageInt, carModel, teamName, currentPoints);
                // Check if the driver already exists
                if (isDuplicateDriver()) {
                    throw new Exception("The driver is already in the program");
                } else if (DataValidation.ageIsNotValid(ageInt)) {
                    throw new Exception("Age must be between 18 and 50");
                } else if (DataValidation.pointsAreNotValid(currentPoints)) {
                    throw new Exception("Current points cannot be negative");
                }
                // Replace the old driver with the updated one
                DriverList.driverList.add(updatedDriver);
                deleteDriver(event);
                errorMsg.setTextFill(Color.GREEN);
                errorMsg.setText("You Successfully updated a driver's detail.");
            }
        }catch (NumberFormatException exception){
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText("Invalid input. Age and Current Points\nmust be Integers.");
        } catch (Exception e) {
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText(e.getMessage());
        }
        fadeErrorMessage();
    }

    // Clear Text Fields
    @FXML
    private void clearTextFields(MouseEvent event) {
        reloadTable();
        clearFields();
    }

    /**
     * Clear the Text Fields
     */
    private void clearFields(){
        ageField.clear();
        currentPointsField.clear();
        carModelField.clear();
        fullNameField.clear();
        teamNameField.clear();
        searchField.clear();
    }

    //Save data to the csv file
    @FXML
    private void saveData(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Set the title, header text and content text
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save your changes?");
        alert.setContentText("When saving data your CSV Files be overwritten.");

        // Add the Save, Don't Save, and Cancel buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(yesButton, cancelButton);

        // Show the alert and wait for the user to make a selection
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                DriverCSVHandler driverHandler = new DriverCSVHandler("src/main/java/csvfiles/Drivers.csv");
                driverHandler.saveData();
                errorMsg.setTextFill(Color.GREEN);
                fadeErrorMessage();
                errorMsg.setText("Saved Data to the File.");
            } else if (response == cancelButton) {
                event.consume();
            }
        });
    }

    // Go back to the Menu
    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        new MainController().navigateToMenu(event);
    }

    @FXML
    private void searchingData(MouseEvent event) {
        // Create a new filtered list to hold the search results
        ObservableList<Driver> filteredData = FXCollections.observableArrayList();
        // Get the original list of drivers from the table
        ObservableList<Driver> originalData = driversTable.getItems();
        driversTable.setItems(filteredData);

        // Set up a listener on the search field
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the filtered list before adding new search results
            filteredData.clear();

            // Loop through the original data and add any matches to the filtered list
            for (Driver data : originalData) {
                if (dataMatchesWithSearch(data, newValue)) {
                    filteredData.add(data);
                }
            }
        });
        driversTable.setItems(filteredData);
    }

    /**
     Checks if the given driver's name contains the given search text.
     @param data the driver to check
     @param newValue the search text to check against
     @return true if the driver's name contains the search text, otherwise false
     */
    private boolean dataMatchesWithSearch(Driver data, String newValue) {
        // checks the search field is empty or null
        if (newValue == null || newValue.isEmpty()) {
            return true; //returns true for get all data
        }
        // preventing case sensitivity
        String lowerCaseFilter = newValue.toLowerCase();
        // returns true if the driver's name contains search text
        if (data.getFullName().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        }
        return false;
    }

    // Retrieving data from the CSV File
    @FXML
    private void retrieveData(MouseEvent event) {
        DriverCSVHandler driverCSVHandler = new DriverCSVHandler("src/main/java/csvfiles/Drivers.csv");
        driverCSVHandler.loadData();
        // display success message
        errorMsg.setTextFill(Color.GREEN);
        errorMsg.setText("Retrieved Data from Text File.");
        fadeErrorMessage();

        reloadTable();
    }

    /**
     *Checks if the driver already exists in the DriverList.
     *@return true if the driver already exists in the DriverList, otherwise false.
     */
    private boolean isDuplicateDriver(){
        boolean isFound = false;
        // Go through the list to find the duplication
        for (int i = 0; i < DriverList.driverList.size(); i++) {
            if (Objects.equals(DriverList.driverList.get(i).fullName, fullNameField.getText()) &&
                    Objects.equals(DriverList.driverList.get(i).age, parseInt(ageField.getText())) &&
                    Objects.equals(DriverList.driverList.get(i).carModel, carModelField.getText()) &&
                    Objects.equals(DriverList.driverList.get(i).teamName, teamNameField.getText()) &&
                    Objects.equals(DriverList.driverList.get(i).currentPoints, parseInt(currentPointsField.getText()))) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    /**
     * Fades out the error message label over a duration of 5 seconds.
     */
    //references : https://docs.oracle.com/javafx/2/api/javafx/animation/FadeTransition.html
    private void fadeErrorMessage(){
        // Create a fade transition animation
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), errorMsg);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(1);
        // Start the transition
        fadeTransition.play();
    }

    /**
     * Reloads the table with the latest driver data.
     */
    private void reloadTable(){
        // Create an ObservableList of drivers sorted by score
        ObservableList<Driver> drivers = FXCollections.observableArrayList(new DriverList().sortByScore());
        // Set the ObservableList as the items for the TableView
        driversTable.setItems(drivers);
    }
}