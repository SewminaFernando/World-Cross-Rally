package code.programmingcw_test1;

import java.util.ArrayList;
import java.util.List;

public class PlayersList {
    public static List<Player> player = new ArrayList<>();

    public List<Player> sortByDate(){
        for (int i = 0; i < player.size() - 1; i++) {
            for (int j = 0; j < player.size() - i - 1; j++) {
                if (player.get(j).getDaysBetween() < player.get(j + 1).getDaysBetween()) {
                    Player temp = player.get(j);
                    player.set(j, player.get(j + 1));
                    player.set(j + 1, temp);
                }
            }
        }
        return player;
    }

}
