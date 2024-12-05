module collegeapp.perezrojocollegeappfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jdk.compiler;

    opens collegeapp.perezrojocollegeappfinal.controller to javafx.fxml;
    exports collegeapp.perezrojocollegeappfinal.controller;
    exports collegeapp.perezrojocollegeappfinal.app;
    opens collegeapp.perezrojocollegeappfinal.app to javafx.fxml;
    exports collegeapp.perezrojocollegeappfinal.config;
    opens collegeapp.perezrojocollegeappfinal.config to javafx.fxml;
}

