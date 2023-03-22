package code.programmingcw_test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.List;
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
        fullName.setCellValueFactory(new PropertyValueFactory<Driver,String>("fullName"));
        age.setCellValueFactory(new PropertyValueFactory<Driver,Integer>("age"));
        carModel.setCellValueFactory(new PropertyValueFactory<Driver,String>("carModel"));
        teamName.setCellValueFactory(new PropertyValueFactory<Driver,String>("teamName"));
        currentPoints.setCellValueFactory(new PropertyValueFactory<Driver,Integer>("currentPoints"));

        // Load data from CSV file into an ObservableList
        
        DriverList driverList = new DriverList();
        List<Driver> list =  driverList.fileReader();
        ObservableList<Driver> drivers = FXCollections.observableArrayList(list);

//        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/csvfiles/Drivers.csv"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] fields = line.split(",");
//                String fullName = fields[0];
//                int age = parseInt(fields[1]);
//                String carModel = fields[2];
//                String teamName = fields[3];
//                int currentPoints = parseInt(fields[4]);
//                Driver driver = new Driver(fullName,age,carModel,teamName,currentPoints);
//                drivers.add(driver);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Set the ObservableList as the items for the TableView
        driversTable.setItems(drivers);


        //Filling the Text Fields when the row click in the table
        driversTable.setOnMouseClicked(event -> {
            if (event.getClickCount()==1){
                Driver driver = driversTable.getSelectionModel().getSelectedItem();
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
        Driver newDriver = new Driver(fullNameField.getText(),parseInt(ageField.getText()),carModelField.getText(), teamNameField.getText(),parseInt(currentPointsField.getText()));

        if (checkEmptyTextFields()){
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText("Please fill all the fields.");
        }else{
            errorMsg.setTextFill(Color.GREEN);
            errorMsg.setText("You Successfully added a driver.");
            driversTable.getItems().add(newDriver);
            clearTextFields(event);
        }
    }

    boolean checkEmptyTextFields(){
        return (ageField.getText().isEmpty() || currentPointsField.getText().isEmpty()
                || fullNameField.getText().isEmpty() || carModelField.getText().isEmpty() ||
                teamNameField.getText().isEmpty());
    }

    @FXML
    void deleteDriver(MouseEvent event) {
        int selectedRow = driversTable.getSelectionModel().getSelectedIndex();
        driversTable.getItems().remove(selectedRow);
        clearTextFields(event);
    }

    @FXML
    void updateDriver(MouseEvent event) {
        Driver newDriver = new Driver(fullNameField.getText(),parseInt(ageField.getText()), carModelField.getText(), teamNameField.getText(),parseInt(currentPointsField.getText()));
        if (checkEmptyTextFields()){
            errorMsg.setTextFill(Color.RED);
            errorMsg.setText("Please fill all the fields.");
        }else {
            errorMsg.setTextFill(Color.GREEN);
            errorMsg.setText("You Successfully updated a driver's detail.");
            driversTable.getItems().add(newDriver);
            deleteDriver(event);
        }
    }

    @FXML
    void clearTextFields(MouseEvent event) {
        ageField.clear();
        currentPointsField.clear();
        carModelField.clear();
        fullNameField.clear();
        teamNameField.clear();
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
    void viewDrivers(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("drivers-list-page.fxml"));
        stage.setScene(new Scene(root, 1200, 832));
    }


    @FXML
    void searchingData(MouseEvent event) {

//        DriverList list = new DriverList();
//        List<Driver> filteredList = list.searchByName(searchField.getText());
//        ObservableList<Driver> drivers = FXCollections.observableArrayList(filteredList);
        //driversTable.setItems(drivers);

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



//        FilteredList <Driver> filteredData = new FilteredList<>(drivers, p -> true); //Pass the data to a filtered list
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(driver -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (driver.getFullName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (driver.getCarModel().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (driver.getTeamName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                }
//                return false;
//            });
//        });

//        SortedList<Driver> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(driversTable.comparatorProperty());
//        ObservableList<Driver> observableFilteredData = FXCollections.observableList(filteredData);

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


}
