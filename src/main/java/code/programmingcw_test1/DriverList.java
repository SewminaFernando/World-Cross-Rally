package code.programmingcw_test1;

import java.util.ArrayList;
import java.util.List;

/**
 * The DriverList class represents a list of drivers in the program.
 * It contains a static list of Driver objects and a method to sort the list by current points.
 */
public class DriverList {

    /**
     A static list of Driver objects used to store driver data.
     */
    public static List<Driver> driverList= new ArrayList<>();

    /**
     * Sorts the list of drivers by current points in descending order.
     * @return sorted driver list
     */
    public List<Driver> sortByScore() {
        Driver temp;
        for (int i = 0; i < driverList.size() - 1; i++) {
            for (int j = 0; j < driverList.size() - i - 1; j++) {
                if (driverList.get(j).getCurrentPoints() < driverList.get(j + 1).getCurrentPoints()) {
                    temp = driverList.get(j);
                    driverList.set(j, driverList.get(j + 1));
                    driverList.set(j + 1, temp);
                }
            }
        }
        return driverList;
    }

}
