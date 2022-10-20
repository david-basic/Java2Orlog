module hr.algebra.java2orlog {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.algebra.java2orlog to javafx.fxml;
    exports hr.algebra.java2orlog;
    exports hr.algebra.java2orlog.controllers;
    opens hr.algebra.java2orlog.controllers to javafx.fxml;
}