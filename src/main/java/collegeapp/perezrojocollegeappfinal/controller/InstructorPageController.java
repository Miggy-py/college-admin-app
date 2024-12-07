package collegeapp.perezrojocollegeappfinal.controller;

import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;
import collegeapp.perezrojocollegeappfinal.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

public class InstructorPageController {



    @FXML
    public ListView instructorListView;
    @FXML
    public ComboBox sortSectionsInstructorPreferences;
    @FXML
    private Label instructorNameLabel;
    @FXML
    private TextField crnTextField;
    @FXML
    private HashMap<DaysOfWeek, CheckBox> dayCheckBoxes;
    @FXML
    private ComboBox<TimeSegments> timeSlotComboBox;
    @FXML
    private ListView<Section> sectionsListView;

    private Instructor instructor;
    private DataCenter dataCenter = DataCenter.getInstance();
    private SchoolData schoolData;
    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() {
        // timeSlotComboBox.setItems(FXCollections.observableArrayList(TimeSegments.values()));
        schoolData = getLatestSchoolData();
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        instructorNameLabel.setText(instructor.getName() + " Preferences");
    }

    @FXML
    private void handleSavePreferences() {
        HashSet<String> crns = new HashSet<>(Arrays.asList(crnTextField.getText().split(",")));
        HashSet<String> days = new HashSet<>(7);

        dayCheckBoxes.forEach((day, checkBox) -> {
            if (checkBox.isSelected()) {
                days.add(day.get());
            }
        });

        EnumSet<TimeSegments> timeSegments = EnumSet.noneOf(TimeSegments.class);
        TimeSegments selectedSegment = timeSlotComboBox.getValue();
        if (selectedSegment != null) {
            timeSegments.add(selectedSegment);
        }

        System.out.println("Preferences saved for instructor: " + instructor.getName());
    }

    @FXML
    private void handleBackToOverview(ActionEvent event) throws IOException {
        // Logic to return to the previous scene (overview screen)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        Parent root = fxmlLoader.load();
        MainViewController mainViewController = fxmlLoader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainViewController.setStage(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private SchoolData getLatestSchoolData() {
        return dataCenter.getSchoolData();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
