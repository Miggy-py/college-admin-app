package collegeapp.perezrojocollegeappfinal.controller;

import collegeapp.perezrojocollegeappfinal.config.SchoolConfiguration;
import collegeapp.perezrojocollegeappfinal.config.SchoolSettings;
import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;
import collegeapp.perezrojocollegeappfinal.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class InstructorPageController {
    public StackPane backgroundStackPane;
    public ImageView schoolLogoImage;
    public Label schoolNameLabel;
    @FXML
    private CheckBox mondayCheckBox, tuesdayCheckBox, wednesdayCheckBox, thursdayCheckBox,
            fridayCheckBox, saturdayCheckBox, sundayCheckBox;
    public CheckBox earlyMorningCheckBox, morningCheckBox, afternoonCheckBox, lateAfternoonCheckBox, eveningCheckBox;
    public ComboBox crnComboBoxManual;
    public Label crnLabel;
    public Label courseAndNumberLabel;
    public Label startAndEndTimeLabel;
    public Label daysOfWeekLabel;
    public Label currentInstructorLabel;
    public ComboBox instructorComboBoxManual;
    public Button updateSectionManuallyButton;
    public TableColumn creditsColumn;
    public ComboBox instructorChoicePreferencesComboBox;
    public TableView<Section> sectionTableView;
    public TableColumn<Section, String> crnColumn;
    public TableColumn<Section, String>  courseColumn;
    public TableColumn<Section, String>  numberColumn;
    public TableColumn<Section, String>  startTimeColumn;
    public TableColumn<Section, String>  endTimeColumn;
    public TableColumn<Section, String>  daysColumn;
    public TableColumn<Section, String>  isOnlineColumn;
    public TableColumn<Section, String>  instructorColumnTV;
    public TableColumn<Instructor, String> idColumn;
    public TableColumn<Instructor, String> nameColumn;
    public TableColumn<Instructor, String> majorTaughtColumn;
    public TableColumn<Instructor, String> creditsTeachingColumn;
    public TableColumn<Instructor, String> hireDateColumn;
    public TableColumn<Instructor, String> preferredCRNsColumn;
    public TableColumn<Instructor, String> preferredDaysColumn;
    public TableColumn<Instructor, String> preferredTimeSegmentsColumn;
    public TableView<Instructor> instructorTableView;
    public Label instructorNamePreferencesLabel;
    public Button savePreferencesButton;
    @FXML
    private TextField crnTextField;
    @FXML
    private HashMap<DaysOfWeek, CheckBox> dayCheckBoxes;
    @FXML
    private HashMap<TimeSegments, CheckBox> timeSegmentsCheckBoxes;

    private DataCenter dataCenter = DataCenter.getInstance();
    private SchoolData schoolData = getLatestSchoolData();
    private Stage stage;
    private Scene scene;


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


    @FXML
    public void initialize() {
        makeSections();
        makeInstructors();
        setSchoolConfigurations();
        schoolData = getLatestSchoolData();
        populateTimeSegmentCheckBoxes();
        populateDayCheckBoxes();
        setData();
    }


    public void setData(){
        schoolData = getLatestSchoolData();
        instructorChoicePreferencesComboBox.getItems().clear();
        instructorChoicePreferencesComboBox.getItems().addAll(schoolData.getInstructorContainer().getInstructors().values());

        crnComboBoxManual.getItems().clear();
        crnComboBoxManual.getItems().addAll(schoolData.getSectionsContainer().getSections().values());

        instructorComboBoxManual.getItems().clear();
        instructorComboBoxManual.getItems().addAll(schoolData.getInstructorContainer().getInstructors().values());

        populateSectionsTableView();
        populateInstructorsTableView();

        instructorTableView.refresh();
        sectionTableView.refresh();
    }


    public void populateSectionsTableView(){
        ObservableList<Section> sectionsData = FXCollections.observableArrayList(
                schoolData.getSectionsContainer().getSections().values()
        );

        sectionTableView.setItems(sectionsData);

        // Set up columns dynamically
        Utility.setupTableColumn(crnColumn, Section::getCrn);
        Utility.setupTableColumn(courseColumn, Section::getCourseName);
        Utility.setupTableColumn(numberColumn, Section::getNumber);
        Utility.setupTableColumn(startTimeColumn, Section::getStartTimeAsString);
        Utility.setupTableColumn(endTimeColumn, Section::getEndTimeAsString);
        Utility.setupTableColumn(daysColumn, Section::getDays);
        Utility.setupTableColumn(isOnlineColumn, section -> section.isOnline() ? "Yes" : "No");
        Utility.setupTableColumn(creditsColumn, Section :: getCredits);
        Utility.setupTableColumn(instructorColumnTV,
                section -> {
                    Instructor instructor = section.getInstructor();
                    return (instructor != null) ? instructor.getNameAsString() : "No Instructor";
                }
        );
    }


    public void populateInstructorsTableView(){
        ObservableList<Instructor> instructorsData = FXCollections.observableArrayList(
                schoolData.getInstructorContainer().getInstructors().values()
        );

        instructorTableView.setItems(instructorsData);

        Utility.setupTableColumn(idColumn, Instructor::getId);
        Utility.setupTableColumn(nameColumn, Instructor::getNameAsString);
        Utility.setupTableColumn(majorTaughtColumn, Instructor::getMajorTaughtAsString);
        Utility.setupTableColumn(creditsTeachingColumn, Instructor::getCreditsAsString);
        Utility.setupTableColumn(hireDateColumn, Instructor::getHireDateAsString);
        Utility.setupTableColumn(preferredCRNsColumn, Instructor :: getPreferredCRNsAsString);
        Utility.setupTableColumn(preferredDaysColumn, Instructor :: getPreferredDaysAsString);
        Utility.setupTableColumn(preferredTimeSegmentsColumn, Instructor :: getPreferredTimeSegmentsAsString);
    }


    private SchoolData getLatestSchoolData() {
        return dataCenter.getSchoolData();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void handleSavePreferences(ActionEvent actionEvent) {
        Instructor selectedInstructor = (Instructor) instructorChoicePreferencesComboBox.getSelectionModel().getSelectedItem();

        if (selectedInstructor != null) {

            String selectedName = selectedInstructor.getNameAsString();
            String selectedId = selectedInstructor.getId();

            System.out.println("Saving preferences for: " + selectedName + " (ID: " + selectedId + ")");

            selectedInstructor.setPreferredCRNs(getCRNsAsSet(crnTextField.getText()));
            selectedInstructor.setPreferredDays(getSelectedDays());
            selectedInstructor.setPreferredTimeSegments(getSelectedTimeSegments());

            setData();

        } else {

            System.out.println("No instructor selected.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Instructor Selected");
            alert.setContentText("Please select an instructor before saving preferences.");
            alert.showAndWait();

        }
    }


    private EnumSet<TimeSegments> getSelectedTimeSegments() {
        return getSelectedEnum(TimeSegments.class, timeSegmentsCheckBoxes);
    }


    private EnumSet<DaysOfWeek> getSelectedDays() {
        return getSelectedEnum(DaysOfWeek.class, dayCheckBoxes);
    }


    public HashSet<String> getCRNsAsSet(String crnString){
        String[] crnArray = crnString.split(",");
        HashSet<String> crnSet = new HashSet<>();

        for (String crn : crnArray) {
            crnSet.add(crn.trim());
        }

        System.out.println("CRNs " + crnSet);
        return crnSet;
    }


    private <E extends Enum<E>> EnumSet<E> getSelectedEnum(Class<E> enumType, Map<E, CheckBox> checkBoxMap) {
        EnumSet<E> selectedEnums = EnumSet.noneOf(enumType); // Start with an empty EnumSet
        for (Map.Entry<E, CheckBox> entry : checkBoxMap.entrySet()) {
            if (entry.getValue().isSelected()) {
                selectedEnums.add(entry.getKey()); // Add the enum key if the checkbox is selected
            }
        }
        System.out.println("Selected " + enumType.getSimpleName() + ": " + selectedEnums);
        return selectedEnums;
    }


    @FXML
    private void handleSectionChosen() {
        Section selectedSection = (Section)crnComboBoxManual.getSelectionModel().getSelectedItem();

        if (selectedSection != null) {
            // Update labels with the selected section's details
            crnLabel.setText(selectedSection.getCrn());
            courseAndNumberLabel.setText(selectedSection.getCourseName() + " " + selectedSection.getNumber());
            startAndEndTimeLabel.setText(selectedSection.getStartTimeAsString() + " - " + selectedSection.getEndTimeAsString());
            daysOfWeekLabel.setText(selectedSection.getDays());
            currentInstructorLabel.setText(
                    selectedSection.getInstructor() != null
                            ? selectedSection.getInstructor().getNameAsString()
                            : "No Instructor Assigned"
            );
        }
    }


    @FXML
    private void handleManualChanges() {
        Section selectedSection = (Section) crnComboBoxManual.getSelectionModel().getSelectedItem();
        Instructor selectedInstructor = (Instructor) instructorComboBoxManual.getSelectionModel().getSelectedItem();
        if (selectedInstructor != null && selectedSection != null) {
            Instructor oldInstructor = selectedSection.getInstructor();
            String oldInstructorName = (oldInstructor != null) ? oldInstructor.getNameAsString() : "No Instructor";
            if(oldInstructor != null){
                oldInstructor.removeCredits(selectedSection.getCredits());
            }
            selectedSection.setInstructor(selectedInstructor);
            currentInstructorLabel.setText(selectedInstructor.getNameAsString());
            selectedInstructor.addToCreditsTeaching(selectedSection.getCredits());

            System.out.println("Swapped " + oldInstructorName + " to " + selectedInstructor.getNameAsString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Change Successful");
            alert.setContentText("Swapped " + oldInstructorName + " to " + selectedInstructor.getNameAsString());
            alert.showAndWait();
            setData();
        } else {
            System.out.println("No instructor selected or section selected.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Instructor or Section Selected");
            alert.setContentText("Please select an instructor or section before saving change.");
            alert.showAndWait();
        }
    }


    public void handleSectionsMatching(ActionEvent actionEvent) {
        matchInstructorsWithSections();
    }


    public void matchInstructorsWithSections() {
        schoolData = getLatestSchoolData();
        ArrayList<Instructor> instructorsToSort = new ArrayList<>(schoolData.getInstructorContainer().getInstructors().values());
        TreeMap<String, Section> sections = new TreeMap<>(schoolData.getSectionsContainer().getSections());

        instructorsToSort.sort(Comparator.comparing(Instructor::getHireDate));

        Set<String> assignedCRNs = new HashSet<>();

        for (Instructor instructor : instructorsToSort) {
            instructor.setCredits(0);
            System.out.println("Matching for Instructor: " + instructor.getName());
            List<Section> assignedSections = new ArrayList<>();
            int totalCredits = 0;

            // Step 1: Assign preferred CRNs
            for (String preferredCRN : instructor.getPreferredCRNs()) {
                if (sections.containsKey(preferredCRN) && !assignedCRNs.contains(preferredCRN)) {
                    Section preferredSection = sections.get(preferredCRN);

                    if (totalCredits + preferredSection.getCredits() <= SchoolSettings.INSTRUCTOR_MAX_CREDITS.getSize() &&
                            !hasTimeConflict(assignedSections, preferredSection)) {

                        System.out.println("  Assigning preferred section: " + preferredCRN);
                        preferredSection.setInstructor(instructor);
                        assignedSections.add(preferredSection);
                        assignedCRNs.add(preferredCRN);
                        totalCredits += preferredSection.getCredits();
                    }
                }
            }

            // Step 2: Assign matching sections
            for (Section section : sections.values()) {
                if (!assignedCRNs.contains(section.getCrn()) &&
                        totalCredits + section.getCredits() <= SchoolSettings.INSTRUCTOR_MAX_CREDITS.getSize() &&
                        instructor.getPreferredTimeSegments().contains(section.getTimeSegment()) &&
                        instructor.getPreferredDays().containsAll(section.getDaysSet()) &&
                        !hasTimeConflict(assignedSections, section)) {

                    System.out.println("  Assigning matching section: " + section.getCrn());
                    section.setInstructor(instructor);
                    assignedSections.add(section);
                    assignedCRNs.add(section.getCrn());
                    totalCredits += section.getCredits();
                }
            }

            // Step 3: Assign leftover sections (ignore preferences)
            for (Section section : sections.values()) {
                if (!assignedCRNs.contains(section.getCrn()) &&
                        totalCredits + section.getCredits() <= SchoolSettings.INSTRUCTOR_MAX_CREDITS.getSize() &&
                        !hasTimeConflict(assignedSections, section)) {

                    System.out.println("  Assigning leftover section: " + section.getCrn());
                    section.setInstructor(instructor);
                    assignedSections.add(section);
                    assignedCRNs.add(section.getCrn());
                    totalCredits += section.getCredits();
                }
            }

            instructor.addToCreditsTeaching(totalCredits);
            System.out.println("  Total credits assigned: " + totalCredits);
        }

        // LOGGING TO SEE WHAT SECTIONS WERE NOT ASSIGNED AND IF IT WAS BECAUSE OF TIME CONFLICT
        for (Section section : sections.values()) {
            if (!assignedCRNs.contains(section.getCrn())) {
                System.out.println("Unassigned Section: " + section.getCrn() +
                        ", Days: " + section.getDaysSet() +
                        ", Time: " + section.getStartTime() + " - " + section.getEndTime());

                // Check if time conflict is the reason
                for (Instructor instructor : instructorsToSort) {
                    List<Section> assignedSections = getSectionsForInstructor(instructor, sections.values());
                    if (hasTimeConflict(assignedSections, section)) {
                        System.out.println("  Conflict found for Section: " + section.getCrn() +
                                " with Instructor: " + instructor.getName());
                    }
                }
            }
        }

        setData(); // Refresh
    }


    private List<Section> getSectionsForInstructor(Instructor instructor, Collection<Section> allSections) {
        List<Section> assignedSections = new ArrayList<>();
        for (Section section : allSections) {
            if (section.getInstructor() != null && section.getInstructor().equals(instructor)) {
                assignedSections.add(section);
            }
        }
        return assignedSections;
    }


    private boolean hasTimeConflict(List<Section> assignedSections, Section newSection) {
        for (Section section : assignedSections) {
            // Check for day overlap
            if (!Collections.disjoint(section.getDaysSet(), newSection.getDaysSet())) {
                // Check for time overlap
                if (!newSection.getStartTime().isAfter(section.getEndTime()) &&
                        !newSection.getEndTime().isBefore(section.getStartTime())) {
                    // Conflict found
                    return true;
                }
            }
        }
        // No time conflict
        return false;
    }


    public void makeSections(){
        if(schoolData.getCourseContainer().getAmountOfCourse() == 0) {
            Utility.createCourse(Major.COMPUTER_SCIENCE, 5);
            Utility.createCourse(Major.COMPUTER_SCIENCE, 5);
            Utility.createCourse(Major.COMPUTER_SCIENCE, 5);
            Utility.createCourse(Major.COMPUTER_SCIENCE, 5);
            Utility.createCourse(Major.COMPUTER_SCIENCE, 5);
        }
    }


    public void makeInstructors(){
        if(schoolData.getInstructorContainer().getNumOfInstructorsMajor(Major.COMPUTER_SCIENCE) == 0) {
            Utility.generateInstructors(Major.COMPUTER_SCIENCE);
        }
    }


    public void setSchoolConfigurations(){
        schoolNameLabel.setText(SchoolConfiguration.NPCC.getNameOfSchool());

        backgroundStackPane.setStyle("-fx-background-color: linear-gradient(" +
                SchoolConfiguration.NPCC.getPrimaryColor()
                + ", " + SchoolConfiguration.NPCC.getSecondaryColor() + ");");

        String logoPath = SchoolConfiguration.NPCC.getLogoPath();

        if (logoPath != null && !logoPath.isEmpty()) {
            schoolLogoImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(logoPath))));
        } else {
            System.err.println("Logo path is not set or invalid!");
        }
    }


    public void populateTimeSegmentCheckBoxes(){
        timeSegmentsCheckBoxes = new HashMap<>();

        timeSegmentsCheckBoxes.put(TimeSegments.EARLY_MORNING, earlyMorningCheckBox);
        timeSegmentsCheckBoxes.put(TimeSegments.MORNING, morningCheckBox);
        timeSegmentsCheckBoxes.put(TimeSegments.AFTERNOON, afternoonCheckBox);
        timeSegmentsCheckBoxes.put(TimeSegments.LATE_AFTERNOON, lateAfternoonCheckBox);
        timeSegmentsCheckBoxes.put(TimeSegments.EVENING, eveningCheckBox);
    }


    public void populateDayCheckBoxes(){
        dayCheckBoxes = new HashMap<>(7);

        dayCheckBoxes.put(DaysOfWeek.MON, mondayCheckBox);
        dayCheckBoxes.put(DaysOfWeek.TUE, tuesdayCheckBox);
        dayCheckBoxes.put(DaysOfWeek.WED, wednesdayCheckBox);
        dayCheckBoxes.put(DaysOfWeek.THU, thursdayCheckBox);
        dayCheckBoxes.put(DaysOfWeek.FRI, fridayCheckBox);
        dayCheckBoxes.put(DaysOfWeek.SAT, saturdayCheckBox);
        dayCheckBoxes.put(DaysOfWeek.SUN, sundayCheckBox);
    }

}
