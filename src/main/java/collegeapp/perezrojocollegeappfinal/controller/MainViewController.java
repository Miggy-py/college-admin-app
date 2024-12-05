package collegeapp.perezrojocollegeappfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    private Stage stage;
    private Scene scene;

    @FXML
    private ImageView backgroundOfMainView;
    @FXML
    private StackPane mainViewStackPane;

    @FXML
    public void initialize() {
        // Binding from 148, keeps the background image the same size as the StackPane in main-view
        backgroundOfMainView.fitWidthProperty().bind(mainViewStackPane.widthProperty());
        backgroundOfMainView.fitHeightProperty().bind(mainViewStackPane.heightProperty());
    }

    @FXML
    public void switchToCollegeInstructorScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/instructor-page.fxml"));
        Parent root = fxmlLoader.load();
        PreferencesController adminReviewController = fxmlLoader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        adminReviewController.setStage(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
