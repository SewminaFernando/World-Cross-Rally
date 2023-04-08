package code.programmingcw_test1;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * The DriverCSVHandler class is a subclass of CSVHandler that handles loading and saving driver data from CSV files.
 */
public class DriverCSVHandler extends CSVHandler {
    /**
     * Constructs a new DriverCSVHandler object with the specified file name.
     * @param fileName the path of the CSV file to load or save driver data from
     */
    public DriverCSVHandler(String fileName) {
        super(fileName);
    }

    /**
     * Loads driver data from the CSV file and stores it in the driverList.
     * Each line of the CSV file contains with the following fields, separated by commas:
     * - Full name of the driver
     * - Age of the driver
     * - Car model of the driver
     * - Team name of the driver
     * - Current points of the driver
     * @throws IOException if there is an error reading the CSV file
     */
    public void loadData() {
        DriverList.driverList.clear();

        String fname,carModel,teamName;
        int currentPoints,age;

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
            String line = null;
            String[] data;
            Driver driver;
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                fname = data[0];
                age = Integer.parseInt(data[1]);
                carModel = data[2];
                teamName = data[3];
                currentPoints = Integer.parseInt(data[4]);
                driver = new Driver(fname,age,carModel,teamName,currentPoints);
                DriverList.driverList.add(driver);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error Reading File\n" + e.getMessage());
        }
    }

    /**
     * Saves driver data to the CSV file from the driverList.
     * Each line of the CSV file will contain the following fields, separated by commas:
     * - Full name of the driver
     * - Age of the driver
     * - Car model of the driver
     * - Team name of the driver
     * - Current points of the driver
     * @throws IOException if there is an error writing to the CSV file
     */
    public void saveData() {
        Writer records = null;
        try {
            records = new FileWriter(fileName,false);
            for (Driver driver :
                    DriverList.driverList) {
                records.write(driver.fullName + "," +
                        driver.age + "," +
                        driver.carModel + "," +
                        driver.teamName + "," +
                        driver.currentPoints + "\n");
            }
            records.close();
        }catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
