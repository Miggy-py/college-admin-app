package main.perezrojocollegeappfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {
    @FXML
    private Stage stage;
    private Scene scene;
    private MediaView mainViewBackgroundVideo;

    @FXML
    public void switchToCollegeInstructorScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("instructor-assignment-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainViewScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
