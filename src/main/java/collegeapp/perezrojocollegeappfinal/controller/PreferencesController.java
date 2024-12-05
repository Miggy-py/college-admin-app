package collegeapp.perezrojocollegeappfinal.controller;

import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;
import collegeapp.perezrojocollegeappfinal.model.DayOfWeek;
import collegeapp.perezrojocollegeappfinal.model.Instructor;
import collegeapp.perezrojocollegeappfinal.model.Section;
import collegeapp.perezrojocollegeappfinal.model.TimeSegments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

public class PreferencesController {
    @FXML
    public ListView instructorListView;
    @FXML
    public ComboBox sortSectionsInstructorPreferences;
    @FXML
    private Label instructorNameLabel;
    @FXML
    private TextField crnTextField;
    @FXML
    private HashMap<DayOfWeek, CheckBox> dayCheckBoxes;
    @FXML
    private ComboBox<TimeSegments> timeSlotComboBox;
    @FXML
    private ListView<Section> sectionsListView;

    private Instructor instructor;
    private DataCenter dataCenter;
    private Stage stage;

    @FXML
    public void initialize() {
        dataCenter = DataCenter.getInstance();
        // timeSlotComboBox.setItems(FXCollections.observableArrayList(TimeSegments.values()));
        loadSections();
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
    private void handleBackToOverview() {
        // Logic to return to the previous scene (overview screen)
    }

    private void loadSections() {

    }

    public void handleHireNewInstructor(ActionEvent actionEvent) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
