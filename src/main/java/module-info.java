module hr.algebra.java2orlog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.naming;
    requires java.xml;


    opens hr.algebra.java2orlog to javafx.fxml;
    exports hr.algebra.java2orlog;
    exports hr.algebra.java2orlog.controllers;
    opens hr.algebra.java2orlog.controllers to javafx.fxml;
    exports hr.algebra.java2orlog.models;
    opens hr.algebra.java2orlog.models to javafx.base;
    exports hr.algebra.java2orlog.rmiserver to java.rmi;
}