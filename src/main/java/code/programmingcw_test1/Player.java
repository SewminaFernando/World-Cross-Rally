package code.programmingcw_test1;

import java.time.LocalDate;

/**
 * This class represent a single player which participated in the random race.
 */
public class Player {
    LocalDate date;
    int rank, currentPoints;
    String location, fullName, teamName;

    /**
     * Construct the Player object by below parameters
     * @param date the date of the race
     * @param location the location the race
     * @param rank the rank of the player
     * @param fullName the full name of the player
     * @param teamName the team name of the player
     * @param currentPoints the updated points of the driver after the race.
     */
    public Player(LocalDate date, String location, int rank, String fullName, String teamName, int currentPoints) {
        this.date = date;
        this.rank = rank;
        this.location = location;
        this.fullName = fullName;
        this.teamName = teamName;
        this.currentPoints = currentPoints;
    }

    /**
     * @return the date of the race
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the rank of the player that gets from the race
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the current points of the player
     */
    public int getCurrentPoints() {
        return currentPoints;
    }

    /**
     * @return the Location of the race
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the full name of the player
     */
    public String getFullName() {
        return fullName;
    }

    public String getTeamName() {
        return teamName;
    }
}
