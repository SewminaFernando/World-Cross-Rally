package code.programmingcw_test1;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;

/**
 * The RaceCSVHandler class is a subclass of CSVHandler that handles loading and saving race data from CSV files.
 */
public class RaceCSVHandler extends CSVHandler {
    /**
     * Constructs a new RaceCSVHandler object with the specified file name.
     * @param fileName the path of the CSV file to load or save race data from
     */
    public RaceCSVHandler(String fileName) {
        super(fileName);
    }

    /**
     * Loads race data from the CSV file and stores it in the playersList.
     * Each line of the CSV file contains with the following fields, separated by commas:
     * - Date of the race (in yyyy-MM-dd format)
     * - Location of the race
     * - Rank of the player in the race
     * - Full name of the player
     * - Team name of the player
     * - Current points of the player
     * @throws IOException if there is an error reading the CSV file
     */
    public void loadData() {
        PlayersList.player.clear();

        String fullName, location, teamName;
        int currentPoints, rank;
        LocalDate date;

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
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
                player = new Player(date,location,rank,fullName,teamName,currentPoints);
                PlayersList.player.add(player);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error Reading File\n" + e.getMessage());
        }
    }

    /**
     * Saves race data to the CSV file from the playerList.
     * Each line of the CSV file will contain the following fields, separated by commas:
     * - Date of the race (in yyyy-MM-dd format)
     * - Location of the race
     * - Rank of the player in the race
     * - Full name of the player
     * - Team name of the player
     * - Current points of the player
     * @throws IOException if there is an error writing to the CSV file
     */
    public void saveData() {
        Writer records = null;
        try {
            records = new FileWriter(fileName,false);
            for (Player player :
                    PlayersList.player) {
                records.write(player.date + "," +
                        player.location + "," +
                        player.rank + "," +
                        player.fullName + "," +
                        player.teamName + "," +
                        player.currentPoints + "\n");
            }
            records.close();
        }catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
