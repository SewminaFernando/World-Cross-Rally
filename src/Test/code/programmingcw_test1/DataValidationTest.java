package code.programmingcw_test1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DataValidationTest {

    @Test
    public void haveEmptyTextFields() {
        // DriverPageControler validators (checks the empty list)
        // All fields are empty
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","","","",""));
        //4 fields are empty
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","","",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","data","","",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","","data","",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","","","data",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","","","","data"));
        // 3 fields are empty
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","data","","",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","data","",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","","data",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","","","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","data","data","",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","data","","data",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","data","","","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","","data","data",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","","data","","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","","","data","data"));
        // 2 fields are empty
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","data","data","",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","data","data",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","","data","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","data","","data",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","data","","","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","data","","data"));
        // 1 Field is empty
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","data","data","data",""));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("","data","data","data","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","","data","data","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","data","","data","data"));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("data","data","data","","data"));
        // Fields are not empty
        Assertions.assertFalse(DataValidation.haveEmptyTextFields("data","data","data","data","data"));


        //SimulateRandomRaceController checks empty fields
        // All fiels are empty
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("",null));
        // One field is empty
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("5",null));
        Assertions.assertTrue(DataValidation.haveEmptyTextFields("", LocalDate.of(2022, 1, 1)));
        // All fields are not empty
        Assertions.assertFalse(DataValidation.haveEmptyTextFields("10", LocalDate.of(2022, 1, 1)));
    }

    @Test
    public void ageIsNotValid() {
        Assertions.assertTrue(DataValidation.ageIsNotValid(12));  //Age is below 18
        Assertions.assertTrue(DataValidation.ageIsNotValid(0));   //Age is 0
        Assertions.assertTrue(DataValidation.ageIsNotValid(56));  //Age is above 50
        Assertions.assertFalse(DataValidation.ageIsNotValid(18)); //Age is 18
        Assertions.assertFalse(DataValidation.ageIsNotValid(50)); //Age is 50
        Assertions.assertFalse(DataValidation.ageIsNotValid(26)); //Age is in between 18 and 50
    }

    @Test
    public void pointsAreNotValid() {
        Assertions.assertTrue(DataValidation.pointsAreNotValid(-10)); //Points are below 0
        Assertions.assertFalse(DataValidation.pointsAreNotValid(0));  //Points are 0
        Assertions.assertFalse(DataValidation.pointsAreNotValid(10)); //Points are above 0
    }
}