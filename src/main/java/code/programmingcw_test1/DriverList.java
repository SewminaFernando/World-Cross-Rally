package code.programmingcw_test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DriverList {

    public List<Driver> fileReader(){
        List<Driver> driverList= new ArrayList<>();
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

    public List<Driver> sortByScore(List<Driver> arrayList) {

        for (int i = 0; i < arrayList.size() - 1; i++) {
            for (int j = 0; j < arrayList.size() - i - 1; j++) {
                if (arrayList.get(j).getCurrentPoints() < arrayList.get(j + 1).getCurrentPoints()) {
                    Driver temp = arrayList.get(j);
                    arrayList.set(j, arrayList.get(j + 1));
                    arrayList.set(j + 1, temp);
                }
            }
        }
        return arrayList;
    }

}
