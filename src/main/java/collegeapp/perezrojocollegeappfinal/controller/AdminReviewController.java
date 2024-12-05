package collegeapp.perezrojocollegeappfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminReviewController {
    public Stage stage;
    public Scene scene;

    @FXML
    public void switchToMainViewScene(ActionEvent event) throws IOException {
        /*
        Can this be done generically?
         */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
        Parent root = fxmlLoader.load();
        MainViewController controller = fxmlLoader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        controller.setStage(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
