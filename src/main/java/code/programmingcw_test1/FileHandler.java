package code.programmingcw_test1;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;

public class FileHandler {

    public void loadDriversFromCSV(){
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlayersFromCSV(){
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



//    public void saveDriversToCSV(){
//        Writer records = null;
//        try {
//            records = new FileWriter("src/main/java/csvfiles/Drivers.csv",false);
//            for (int i = 0; i<driversTable.getItems().size(); i++){
//                records.write(fullName.getCellData(i)+","+
//                        age.getCellData(i)+","+
//                        carModel.getCellData(i)+","+
//                        teamName.getCellData(i)+","+
//                        currentPoints.getCellData(i)+"\n");
//            }
//            records.close();
//        }catch (IOException e){
//            throw new RuntimeException(e);
//        }
//    }
}
