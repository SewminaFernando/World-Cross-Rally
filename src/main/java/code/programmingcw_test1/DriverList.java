package code.programmingcw_test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DriverList {
    List<Driver> driverList= new ArrayList<>();

    public void fileReader(){
        String fname,age,carModel,teamName,currentPoints;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/csvfiles/Drivers.csv"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                fname = data[0];
                age = data[1];
                carModel = data[2];
                teamName = data[3];
                currentPoints = data[4];
                Driver driver = new Driver(age,currentPoints,fname,carModel,teamName);
                driverList.add(driver);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
