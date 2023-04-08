package code.programmingcw_test1;

/**
 * Driver class represents a single driver in the program
 * It contains each driver's Full Name, Age, Team Name, Car Model and Current Points.
 */
public class Driver {

    String fullName,carModel,teamName;
    int age,currentPoints;

    /**
     * Construct the Driver object by below parameters
     * @param fullName the full name of the driver
     * @param age the age of the driver
     * @param carModel the car model of the driver
     * @param teamName the team name of the driver
     * @param currentPoints the current points of the driver
     */
    public Driver(String fullName, int age, String carModel, String teamName, int currentPoints) {
        this.age = age;
        this.currentPoints = currentPoints;
        this.fullName = fullName;
        this.carModel = carModel;
        this.teamName = teamName;
    }

    /**
     * @return the age of the driver
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the current points of the driver
     */
    public int getCurrentPoints() {
        return currentPoints;
    }

    /**
     * @return the full name of the driver
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return the Car Model of the car
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * @return the Team Name of the driver
     */
    public String getTeamName() {
        return teamName;
    }
}
