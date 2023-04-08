package code.programmingcw_test1;

import java.time.LocalDate;

/**
 * This class contains methods to validate the user inputs
 */
public class DataValidation {
    /**
     * Checks if any of the below text fields are empty.
     * @param age the age text field
     * @param currentPoints the current points text field
     * @param fullName the full name text field
     * @param carModel the car model text field
     * @param teamName the team name text field
     * @return true if any of the text fields are empty, otherwise false
     */
    public static boolean haveEmptyTextFields(String age, String currentPoints, String fullName, String carModel, String teamName){
        return (age.isEmpty() ||
                currentPoints.isEmpty() ||
                fullName.isEmpty() ||
                carModel.isEmpty() ||
                teamName.isEmpty());
    }

    /**
     * Checks if the below text fields are empty.
     * @param noOfParticipants the number of participants text field
     * @param date the date of the race
     * @return true if the text fields are empty, otherwise false
     */
    public static boolean haveEmptyTextFields(String noOfParticipants, LocalDate date){
        return (noOfParticipants.isEmpty() || date == null);
    }

    /**
     * Checks if the entered age is between 18-50 range.
     * @param age the age to check
     * @return true if the age is not within the valid range, otherwise false.
     */
    public static boolean ageIsNotValid(int age){
        return (age > 50 ||age < 18);
    }

    /**
     * Checks if the entered points are not negative.
     * @param points the points to check
     * @return true if the points are not valid, otherwise false
     */
    public static boolean pointsAreNotValid(int points){
        return (points < 0);
    }
}
