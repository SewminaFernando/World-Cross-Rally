package code.programmingcw_test1;

public class Driver {

    String fullName,carModel,teamName;
    int age,currentPoints;

    //Adding constructors

    public Driver(String fullName, int age, String carModel, String teamName, int currentPoints) {
        this.age = age;
        this.currentPoints = currentPoints;
        this.fullName = fullName;
        this.carModel = carModel;
        this.teamName = teamName;
    }

    //Getter Methods
    public int getAge() {
        return age;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getTeamName() {
        return teamName;
    }
}
