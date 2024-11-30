module lecture.perezrojocollegeappfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens main.perezrojocollegeappfinal to javafx.fxml;
    exports main.perezrojocollegeappfinal.controller;
    opens main.perezrojocollegeappfinal.controller to javafx.fxml;
    exports main.perezrojocollegeappfinal.app;
    opens main.perezrojocollegeappfinal.app to javafx.fxml;
    exports main.perezrojocollegeappfinal.config;
    opens main.perezrojocollegeappfinal.config to javafx.fxml;
}