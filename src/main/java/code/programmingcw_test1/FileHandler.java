package code.programmingcw_test1;

import java.io.*;
import java.time.LocalDate;

public class FileHandler {

    public static void loadDriversFromCSV(){
        DriverList.driverList.clear();

        String fname,carModel,teamName;
        int currentPoints,age;

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader("src/main/java/csvfiles/Drivers.csv"));
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

    public static void loadPlayersFromCSV(){
        String fullName, location, teamName;
        int currentPoints, rank, daysBetween;
        LocalDate date;

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader("src/main/java/csvfiles/PastRandomRaces.csv"));
            String line = null;
            String[] data;
            Player player;
            while ((line = reader.readLine()) != null){
                data = line.split(",");
                date = LocalDate.parse(data[0]);
                location = data[1];
                rank = Integer.parseInt(data[2]);
                fullName = data[3];
                teamName = data[4];
                currentPoints = Integer.parseInt(data[5]);
                daysBetween = Integer.parseInt(data[6]);
                player = new Player(date,location,rank,fullName,teamName,currentPoints,daysBetween);
                PlayersList.player.add(player);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error Reading File\n" + e.getMessage());
        }
    }
    public static void saveDriversToCSV(){
        Writer records = null;
        try {
            records = new FileWriter("src/main/java/csvfiles/Drivers.csv",false);
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

    public static void saveRaceDetailsToCSV(){
        Writer records = null;
        try {
            records = new FileWriter("src/main/java/csvfiles/PastRandomRaces.csv",false);
            for (Player player :
                    PlayersList.player) {
                records.write(player.date + "," +
                        player.location + "," +
                        player.rank + "," +
                        player.fullName + "," +
                        player.teamName + "," +
                        player.currentPoints + "," +
                        player.daysBetween +"\n");
            }
            records.close();
        }catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
