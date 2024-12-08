package collegeapp.perezrojocollegeappfinal.app;

import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("JavaFX runtime version: " + System.getProperty("javafx.version"));
        System.out.println("JDK version: " + System.getProperty("java.version"));
        System.out.println("JDK vendor: " + System.getProperty("java.vendor"));
        System.out.println("JDK home: " + System.getProperty("java.home"));

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CourseFlow");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            DataCenter.getInstance().save();
        });
    }

    public static void main(String[] args) {
        launch();
        System.out.println(DataCenter.getInstance().getSchoolData().getSectionsContainer().toString());
    }
}
