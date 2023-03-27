package code.programmingcw_test1;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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



    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Set up the table columns
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        carModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        teamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        currentPoints.setCellValueFactory(new PropertyValueFactory<>("currentPoints"));

        ObservableList<Driver> drivers = FXCollections.observableArrayList(DriverList.driverList);
        // Set the ObservableList as the items for the TableView
        driversTable.setItems(drivers);


        //Filling the Text Fields when the row click in the table
        driversTable.setOnMouseClicked(event -> {
            if (event.getClickCount()==1){
                Driver driver = driversTable.getSelectionModel().getSelectedItem();
                //driversTable.setItems(drivers);
                if (driver != null){
                    fullNameField.setText(driver.getFullName());
                    ageField.setText(Integer.toString(driver.getAge()));
                    carModelField.setText(driver.getCarModel());
                    teamNameField.setText(driver.getTeamName());
                    currentPointsField.setText(Integer.toString(driver.getCurrentPoints()));
                }
            }
        });
    }

    @FXML
    void addDriver(MouseEvent event) {
        try {
            if (checkEmptyTextFields()) {
                throw new Exception("Please fill all the fields.");
            }
            if (isDuplicateDriver()) {
                throw new Exception("The driver is already in the program.");
            }
            int age = parseInt(ageField.getText());
            if (age < 18 || age > 50) {
                throw new Exception("Age must be between 18 and 50.");
            }
            int currentPoints = parseInt(currentPointsField.getText());
            if (currentPoints < 0) {
                throw new Exception("Current points cannot be negative.");
            }
            errorMsg.setTextFill(Color.GREEN);
            errorMsg.setText("You Successfully added a driver.");
            Driver newDriver = new Driver(fullNameField.getText(), age, carModelField.getText(), teamNameField.getText(), currentPoints);
            DriverList.driverList.add(newDriver);
            ObservableList<Driver> drivers = FXCollections.observableArrayList(new DriverList().sortByScore());
            // Set the ObservableList as the items for the TableView
            driversTable.setItems(drivers);
            driversTable.refresh();
            clearTextFields(event);
        }catch (NumberFormatException exception){
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText("Invalid input. Age and Current Points\nmust be Integers.");
        } catch (Exception e) {
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText(e.getMessage());
        }
        fadeErrorMessage();
    }

    boolean checkEmptyTextFields(){
        return (ageField.getText().isEmpty() ||
                currentPointsField.getText().isEmpty() ||
                fullNameField.getText().isEmpty() ||
                carModelField.getText().isEmpty() ||
                teamNameField.getText().isEmpty());
    }

    @FXML
    void deleteDriver(MouseEvent event) {
        // get the selected item from the table view
        Driver selectedItem = driversTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // get the cell data for each column of the selected item
            String deleteName = fullName.getCellData(selectedItem);
            int deleteAge = age.getCellData(selectedItem);
            String deleteCar = carModel.getCellData(selectedItem);
            String deleteTeam = teamName.getCellData(selectedItem);
            int deletedPoints = currentPoints.getCellData(selectedItem);

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
            ObservableList<Driver> drivers = FXCollections.observableArrayList(new DriverList().sortByScore());
            // Set the ObservableList as the items for the TableView
            driversTable.setItems(drivers);
            clearTextFields(event);
        }
    }

    @FXML
    void updateDriver(MouseEvent event) {
        try {
            if (checkEmptyTextFields()) {
                throw new Exception("Please fill all the fields.");
            } else {
                Driver updatedDriver = new Driver(fullNameField.getText(), parseInt(ageField.getText()), carModelField.getText(), teamNameField.getText(), parseInt(currentPointsField.getText()));
                if (isDuplicateDriver()) {
                    throw new Exception("The driver already exists in the program");
                } else if (parseInt(ageField.getText()) < 18 || parseInt(ageField.getText()) > 50) {
                    throw new Exception("Age must be between 18 and 50");
                } else if (parseInt(currentPointsField.getText()) < 0) {
                    throw new Exception("Current points cannot be negative");
                }
                DriverList.driverList.add(updatedDriver);
                deleteDriver(event);
                driversTable.refresh();
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

    @FXML
    void clearTextFields(MouseEvent event) {
        ageField.clear();
        currentPointsField.clear();
        carModelField.clear();
        fullNameField.clear();
        teamNameField.clear();
        searchField.clear();
    }

    //Save data to the csv file
    @FXML
    void saveData(MouseEvent event) {
        Writer records = null;
        try {
            records = new FileWriter("src/main/java/csvfiles/Drivers.csv",false);
            for (int i = 0; i<driversTable.getItems().size(); i++){
                records.write(fullName.getCellData(i)+","+
                        age.getCellData(i)+","+
                        carModel.getCellData(i)+","+
                        teamName.getCellData(i)+","+
                        currentPoints.getCellData(i)+"\n");
            }
            records.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        new MainController().navigateToMenu(event);
    }

    @FXML
    void searchingData(MouseEvent event) {
        ObservableList<Driver> filteredData = FXCollections.observableArrayList();
        ObservableList<Driver> originalData = driversTable.getItems();
        driversTable.setItems(filteredData);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.clear();

            for (Driver data : originalData) {
                if (dataMatchesSearchCriteria(data, newValue)) {
                    filteredData.add(data);
                }
            }
        });
        driversTable.setItems(filteredData);
    }

    private boolean dataMatchesSearchCriteria(Driver data, String newValue) {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
        String lowerCaseFilter = newValue.toLowerCase();
        if (data.getFullName().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        }
        return false;
    }

    @FXML
    void retrieveData(MouseEvent event) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.loadDriversFromCSV();

        ObservableList<Driver> drivers = FXCollections.observableArrayList(new DriverList().sortByScore());

        // Set the ObservableList as the items for the TableView
        driversTable.setItems(drivers);
    }

    private boolean isDuplicateDriver(){
        boolean isFound = false;
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
    private void fadeErrorMessage(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), errorMsg);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
    }
}