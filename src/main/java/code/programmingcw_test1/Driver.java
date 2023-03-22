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

    //setters
    public void setAge(int age) {
        this.age = age;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    //getters


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
