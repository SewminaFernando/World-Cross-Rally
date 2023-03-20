module code.programmingcw_test1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens code.programmingcw_test1 to javafx.fxml;
    exports code.programmingcw_test1;
}