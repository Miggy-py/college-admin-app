package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.config.SchoolSettings;
import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;

public class Utility implements Serializable {
    private static final Random rand = new Random();
    private static DataCenter dc = DataCenter.getInstance();

    /**
     * Sets up a TableColumn with a cellValueFactory that extracts data using the provided extractor function.
     *
     * @param column    The TableColumn to configure
     * @param extractor A function that extracts the property to display
     * @param <T>       The type of objects in the TableView
     * @param <R>       The type of the property to extract
     */
    public static <T, R> void setupTableColumn(TableColumn<T, R> column, Function<T, R> extractor) {
        column.setCellValueFactory(cellData -> {
            T rowObject = cellData.getValue(); // Get the row object
            R value = extractor.apply(rowObject); // Extract the property
            return new SimpleObjectProperty<>(value); // Wrap in an ObservableValue
        });
    }

    public static <K, V, R> ArrayList<R> getAllOfFrom(Map<K, V> map, Function<V, R> extractor) {
        ArrayList<R> result = new ArrayList<>();
        for (V value : map.values()) {
            result.add(extractor.apply(value));
        }
        return result;
    }

    public static <T, R> ArrayList<R> getAllOfFrom(Collection<T> items, Function<T, R> extractor) {
        ArrayList<R> extractedProperties = new ArrayList<>();
        for (T item : items) {
            extractedProperties.add(extractor.apply(item));
        }
        return extractedProperties;
    }

    public static Course createCourse(Major major, int numOfSections){
        String courseNumber = "" + rand.nextInt(100, 300);
        int randomCredit = rand.nextInt(1,5);

        Course newCourse = new Course(
                major.getCourseName(),
                courseNumber,
                "This is " + courseNumber,
                randomCredit,
                createSections(major, numOfSections, courseNumber, randomCredit));

        dc.getSchoolData().addCourse(newCourse);

        return newCourse;
    }


    public static SectionsContainer createSections(Major major, int numOfSections, String courseNumber, int credit){
        SectionsContainer newSections = new SectionsContainer();
        HashSet<String> uniqueCRNS = createUniqueCRNs(numOfSections);

        for (int i = 0; i < numOfSections; i++) {
            boolean isOnline = rand.nextBoolean();
            HashSet<String> studentIds = new HashSet<>(); // fix the size
            TimeRange time = generateTimeRange();
            String[] crnsToAdd = uniqueCRNS.toArray(new String[0]);

            Section newSectionToAdd = new Section(
                    time,
                    getTimeSegment(time.getStartTime()),
                    randomClassDays(),
                    crnsToAdd[i],
                    null,
                    generateTextbookContainer(major),
                    major,
                    courseNumber,
                    generateClassroom(major),
                    isOnline,
                    studentIds,
                    credit);

            newSections.addSection(newSectionToAdd);
            dc.getSchoolData().addSection(newSectionToAdd);
        }

        return newSections;
    }
    /*

    public static InstructorContainer generateInstructors(Major major, int numOfInstructors){
        InstructorContainer newInstructors = new InstructorContainer(numOfInstructors);

        for (int i = 0; i < numOfInstructors; i++) {
            Name name = generateRandomName();
            LocalDate
        }
    }


    private static void populateSection(Major major){

    }


    // SHORTEN THIS
    private static Student makeDummyStudent(Major major){
        Name name = generateRandomName();
        double gpa = rand.nextDouble(1.5, 4.1);
        int creditsCompleted = rand.nextInt(64);

        SectionsContainer sections = dc.getSectionsContainer();

        Section[] sectionsOfStudentMajor = sections.getSectionsOfMajor(major);


        if(sectionsOfStudentMajor.length == 0){
            return Optional.empty();
        }

        // Makes sure that the while loop won't go on forever by making sure the student can enroll into
        // enough sections
        int maxSections = Math.min(SchoolSettings.MAX_SECTIONS_PER_STUDENT.getSize(), sectionsOfStudentMajor.length);

        SectionsContainer enrolledInSections = new SectionsContainer();

        while (enrolledInSections.getNumOfSections() < maxSections) {
            Section section = sectionsOfStudentMajor[rand.nextInt(sectionsOfStudentMajor.length)];
            if (!studentAlreadyInSection(section, enrolledInSections)) {
                enrolledInSections.addSection(section);
            }
        }

        Student dummy = new Student(name, major,gpa, creditsCompleted, enrolledInSections);
        Section[] studentSections = enrolledInSections.getAllSections();

        for(int i = 0; i < studentSections.length - 1; i++){
            studentSections[i].addStudentId(dummy.getId());
        }

        dc.enrollStudent(dummy);

        return Optional.of(dummy);
    }


    private static boolean studentAlreadyInSection(Section section, SectionsContainer enrolledInSections) {
        for (Section enrolledSection : enrolledInSections.getAllSections()) {
            if (enrolledSection.getCrn().equals(section.getCrn())) {
                return true;
            }
        }
        return false;
    }
     */


    private static Classroom generateClassroom(Major major){
        String room = "" + rand.nextInt(100, 399);
        String building = major.getAsssociatedBuilding();
        int capacity = rand.nextInt(26, SchoolSettings.MAX_CLASSROOM_SEATS.getSize());
        boolean hasProjector = rand.nextBoolean();

        return new Classroom(room, building, capacity, hasProjector);
    }


    private static TextbookContainer generateTextbookContainer(Major major){
        TextbookContainer backpack = new TextbookContainer();

        // Allows for only a max number of textbooks, college decides
        int numberOfTextbooks = rand.nextInt(0, SchoolSettings.MAX_TEXTBOOKS_PER_SECTION.getSize() + 1);

        for (int i = 0; i < numberOfTextbooks; i++) {
            Textbook textbook = new Textbook(major + "Edition " + i, generateRandomName());
            backpack.addBook(textbook);
        }

        return backpack;
    }


    public static void generateInstructors(Major major){
        InstructorContainer instructors = dc.getSchoolData().getInstructorContainer();

        // Hire as many new instructors of required major as allowed :)
        for(int i = 0; i < SchoolSettings.MAX_INSTRUCTORS_PER_COURSE.getSize(); i++){
            int year = rand.nextInt(1990, 2025); // Random year between 1990 and 2024
            int month = rand.nextInt(1, 13);     // Random month between 1 and 12

            // Get the maximum days in the generated month and year
            int maxDay = YearMonth.of(year, month).lengthOfMonth();
            int day = rand.nextInt(1, maxDay + 1); // Random day within valid range

            Instructor hiredInstructor = new Instructor(generateRandomName(), major, LocalDate.of(year, month, day));
            instructors.addInstructor(hiredInstructor.getId(), hiredInstructor);
        }
    }


    /*
    At first, this method generateRandomNames always loaded in the txt files every single time into the ArrayLists.
    To fix this, placing the loadNames part of the method into the DataCenter and the ArrayLists there, the
    lists only have to be populated once. Greatly improved time complexity.
     */

    // Uses the preloaded names in an ArrayList size slightly larger than real size for a random name
    private static Name generateRandomName() {
        ArrayList<String> firstNames = dc.getFIRST_NAMES();
        ArrayList<String> lastNames = dc.getLAST_NAMES();

        String firstName = firstNames.get(rand.nextInt(firstNames.size()));
        String lastName = lastNames.get(rand.nextInt(lastNames.size()));

        return new Name(firstName, lastName);
    }


    // Uses HashSet to make sure there are no duplicates with also a known size
    private static LinkedHashSet<String> createUniqueCRNs(int numOfSections){
        LinkedHashSet<String> uniqueCRNs = new LinkedHashSet<>(numOfSections);

        while(uniqueCRNs.size() < numOfSections){
            String randomCRN = "" + rand.nextInt(SchoolSettings.LOWER_CRN.getSize(), SchoolSettings.MAX_CRN.getSize());
            uniqueCRNs.add(randomCRN);
        }

        return uniqueCRNs;
    }


    /*
    Instead of trying to make a Set of random days and then having it be sorted,
    we can populate it at first and then take random days out to minimize it down
    to 2-4 days of class and using a LinkedHashSet to maintain order. Thus, removing
    the strain of ever having to sort the days. Using another List to hold the DaysOfWeek
    allows us to randomly remove Days, not have to resize, and leads to no duplication
    removal.
     */
    private static LinkedHashSet<DaysOfWeek> randomClassDays(){
        // Select 2 or 4 random days for the class
        int numberOfDays = rand.nextInt(2,5);

        LinkedHashSet<DaysOfWeek> classDays = new LinkedHashSet<>(7);
        classDays.addAll(Arrays.asList(DaysOfWeek.values()));

        ArrayList<DaysOfWeek> listOfDays = new ArrayList<>(classDays);

        while (classDays.size() > numberOfDays) {
            DaysOfWeek randomDayToRemove = listOfDays.remove(rand.nextInt(listOfDays.size()));
            classDays.remove(randomDayToRemove);
        }

        return classDays;
    }


    // Uses TimeSegments Enum to generate a random startTime and endTime
    private static TimeRange generateTimeRange(){
        int earliestStartHour = TimeSegments.EARLY_MORNING.getStartTime().getHour();
        int latestStartHour = TimeSegments.EVENING.getStartTime().getHour();

        int startHour = rand.nextInt(earliestStartHour, latestStartHour + 1);
        int startMinute = rand.nextInt(4) * 15; // 15 Minute Intervals

        LocalTime startTime = LocalTime.of(startHour, startMinute);
        LocalTime endTime = startTime.plusHours(rand.nextInt(1, 4)); // Duration 1 to 3 hours

        return new TimeRange(startTime, endTime);
    }


    private static TimeSegments getTimeSegment(LocalTime startTime){
        return TimeSegments.getSegmentForTime(startTime);
    }
}
