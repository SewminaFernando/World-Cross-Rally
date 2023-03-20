package code.programmingcw_test1;

public class Driver {

    String fullName,carModel,teamName,age,currentPoints;

    //Adding constructors

    public Driver(String age, String currentPoints, String fullName, String carModel, String teamName) {
        this.age = age;
        this.currentPoints = currentPoints;
        this.fullName = fullName;
        this.carModel = carModel;
        this.teamName = teamName;
    }

    //setters
    public void setAge(String age) {
        this.age = age;
    }

    public void setCurrentPoints(String currentPoints) {
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


    public String getAge() {
        return age;
    }

    public String getCurrentPoints() {
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
