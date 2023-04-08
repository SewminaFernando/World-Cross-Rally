package code.programmingcw_test1;

import java.util.ArrayList;
import java.util.List;

/**
 * The PlayerList class represents a list of players which participated in races.
 * It contains a static list of Player objects and a method to sort the list by date of the race held.
 */
public class PlayersList {
    /**
     A static list of Player objects used to store player data.
     */
    public static List<Player> player = new ArrayList<>();

    /**
     * Sorts the list of players by the date of the race.
     * @return sorted players list
     */
    public List<Player> sortByDate(){
        Player temp;
        for (int i = 0; i < player.size() - 1; i++) {
            for (int j = 0; j < player.size() - i - 1; j++) {
                if (player.get(j).date.isBefore(player.get(j + 1).date)) {
                    temp = player.get(j);
                    player.set(j, player.get(j + 1));
                    player.set(j + 1, temp);
                }
            }
        }
        return player;
    }
}
