package code.programmingcw_test1;

import java.time.LocalDate;

public class Player {
    LocalDate date;
    int rank;
    String location;
    String fullName;
    String teamName;
    int currentPoints;
    int daysBetween;

    public Player(LocalDate date, String location, int rank, String fullName, String teamName, int currentPoints, int daysBetween) {
        this.date = date;
        this.rank = rank;
        this.location = location;
        this.fullName = fullName;
        this.teamName = teamName;
        this.currentPoints = currentPoints;
        this.daysBetween = daysBetween;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getRank() {
        return rank;
    }

    public String getLocation() {
        return location;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public int getDaysBetween() {
        return daysBetween;
    }
}
