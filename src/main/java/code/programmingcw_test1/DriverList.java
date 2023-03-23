package code.programmingcw_test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DriverList {

    public static List<Driver> driverList= new ArrayList<>();

    public List<Driver> sortByScore() {

        for (int i = 0; i < driverList.size() - 1; i++) {
            for (int j = 0; j < driverList.size() - i - 1; j++) {
                if (driverList.get(j).getCurrentPoints() < driverList.get(j + 1).getCurrentPoints()) {
                    Driver temp = driverList.get(j);
                    driverList.set(j, driverList.get(j + 1));
                    driverList.set(j + 1, temp);
                }
            }
        }
        return driverList;
    }

}
