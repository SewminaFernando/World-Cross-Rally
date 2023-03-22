package code.programmingcw_test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DriverList {
    List<Driver> driverList= new ArrayList<>();
    public List<Driver> fileReader(){



        String fname,carModel,teamName;
        int currentPoints,age;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/csvfiles/Drivers.csv"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                fname = data[0];
                age = Integer.parseInt(data[1]);
                carModel = data[2];
                teamName = data[3];
                currentPoints = Integer.parseInt(data[4]);
                Driver driver = new Driver(fname,age,carModel,teamName,currentPoints);
                driverList.add(driver);
            }
            reader.close();
            return driverList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Driver> searchByName(String name) {
        List<Driver> exactMatch = new ArrayList<>();
        List<Driver> substringMatch = new ArrayList<>();

        for (Driver driver : driverList) {
            if (driver.fullName.equals(name)) {
                exactMatch.add(driver);
            } else if (driver.fullName.contains(name)) {
                substringMatch.add(driver);
            }
        }

        exactMatch.addAll(substringMatch);
        return exactMatch;
    }

}
