package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;
import collegeapp.perezrojocollegeappfinal.model.*;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;

public class Utility {
    private static final Random rand = new Random();
    private static final DataCenter dc = DataCenter.getInstance();

    public static Course createCourse(Major major, int numOfSections){
        String courseNumber = "" + rand.nextInt(100, 300);
        int randomCredit = rand.nextInt(1,5);

        Course c = new Course(
                major.getCourseName(),
                courseNumber,
                "This is " + courseNumber,
                randomCredit,
                createSections(major, numOfSections, courseNumber));

        dc.addCourse(c);

        return c;
    }

    /*
    These 3 functions originally did not use the performAction method but instead just had very similar repeated code.

    First parameter is what exactly will identify the element we're looking for, here it's either and ID or CRN title.

    Second parameter is the interface function which takes in our String idOrTitle and gives back a result in a generic
    E[] array since that is what GenericBag gives back when for example removeShifting using the removeSection method
    defined in our SectionsContainer.

    Then our 3rd and 4th parameter are just what to return, success or failure.
     */
    public static <E> String performAction(String idOrTitle, Function<String, E[]> action, String successMessage, String failureMessage) {
        E[] result = action.apply(idOrTitle);
        return result.length != 0 ? Arrays.toString(result) + " " + successMessage : failureMessage;
    }

    public static String removeSection(String crn){
        SectionsContainer sc = dc.getSectionsContainer();
        return performAction(crn, sc::removeSection, "REMOVED", "Section not found");
    }

    public static String expelStudent(String id) {
        StudentsEnrolled si = dc.getStudentsEnrolled();
        return performAction(id, si::expelStudent, "EXPELLED", "Student not found");
    }

    public static String fireInstructor(String id) {
        InstructorList il = dc.getInstructorList();
        return performAction(id, il::fireInstructor, "FIRED", "Instructor not found");
    }

    public static String hireInstructor(Name name, Major major) {
        if(dc.getInstructorList().getNumOfInstructorsMajor(major) == Settings.MAX_PROFESSORS_PER_COURSE.getSize()){
            return "Max Instructors Reached";
        }

        Instructor instructor = new Instructor(name, major);

        dc.hireInstructor(instructor);
        return "Instructor Hired";
    }

    public static String removeCourse(String courseTitle, String courseNumber) {
        CourseContainer c = dc.getCourses();
        return performAction(courseTitle + " " + courseNumber, title -> c.removeCourse(courseTitle, courseNumber), "REMOVED", "Course not found");
    }

    public static SectionsContainer createSections(Major major, int numOfSections, String courseNumber){
        SectionsContainer newSections = new SectionsContainer();
        ArrayList<String> uniqueCRNS = createUniqueCRNs(numOfSections);

        for (int i = 0; i < numOfSections; i++) {
            boolean isOnline = rand.nextBoolean();
            ArrayList<String> studentIds = new ArrayList<>();
            TimeRange time = generateTimeRange();

            Section s = new Section(
                    time,
                    getTimeSegment(time.getStartTime()),
                    randomClassDays(),
                    uniqueCRNS.get(i),
                    getInstructor(major),
                    generateTextbookContainer(major),
                    major,
                    courseNumber,
                    generateClassroom(major),
                    isOnline,
                    studentIds);

            newSections.addSection(s);
            dc.addSection(s);
        }

        return newSections;
    }

    // SHORTEN THIS
    public static Optional<Student> makeDummyStudent(Major major){
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
        int maxSections = Math.min(Settings.MAX_SECTIONS_PER_STUDENT.getSize(), sectionsOfStudentMajor.length);

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

    private static Classroom generateClassroom(Major major){
        String room = "" + rand.nextInt(100, 399);
        String building = major.getAsssociatedBuilding();
        int capacity = rand.nextInt(26, Settings.MAX_CLASSROOM_SEATS.getSize());
        boolean hasProjector = rand.nextBoolean();

        return new Classroom(room, building, capacity, hasProjector);
    }

    private static TextbookContainer generateTextbookContainer(Major major){
        TextbookContainer backpack = new TextbookContainer();

        // Allows for only a max number of textbooks, college decides
        int numberOfTextbooks = rand.nextInt(0, Settings.MAX_TEXTBOOKS_PER_SECTION.getSize() + 1);

        for (int i = 0; i < numberOfTextbooks; i++) {
            Textbook textbook = new Textbook(major + "Edition " + i, generateRandomName());
            backpack.addBook(textbook);
        }

        return backpack;
    }

    private static Instructor getInstructor(Major major){
        InstructorList instructors = dc.getInstructorList();

        if(instructors.getNumOfInstructorsMajor(major) == 0){
            // Hire as many new instructors of required major as allowed :)
            for(int i = 0; i < Settings.MAX_PROFESSORS_PER_COURSE.getSize(); i++){
                instructors.addInstructor(new Instructor(generateRandomName(), major));
            }
        }

        return instructors.getRandomInstructor(major);
    }

    /*
    At first, this method generateRandomNames always loaded in the txt files every single time into the ArrayLists.
    To fix this, placing the loadNames part of the method into the DataCenter and the ArrayLists there, the
    lists only have to be populated once. Greatly reduced the time taken to run program.
     */
    private static Name generateRandomName() {
        ArrayList<String> firstNames = dc.getFirstNames();
        ArrayList<String> lastNames = dc.getLastNames();

        String firstName = firstNames.get(rand.nextInt(firstNames.size()));
        String lastName = lastNames.get(rand.nextInt(lastNames.size()));

        return new Name(firstName, lastName);
    }

    private static ArrayList<String> createUniqueCRNs(int numOfSections){
        ArrayList<String> checkedCRNs = new ArrayList<>(numOfSections);

        while(checkedCRNs.size() < numOfSections){
            String randomCRN = "" + rand.nextInt(Settings.LOWER_CRN.getSize(), Settings.MAX_CRN.getSize());
            if(!checkedCRNs.contains(randomCRN)){
                checkedCRNs.add(randomCRN);
            }
        }
        return checkedCRNs;
    }

    private static ArrayList<DayOfWeek> randomClassDays(){
        ArrayList<DayOfWeek> classDays = new ArrayList<>();
        DayOfWeek[] allDays = DayOfWeek.values();

        // Select 2 or 3 random days for the class
        int numDays = rand.nextInt(2,4);
        while (classDays.size() < numDays) {
            DayOfWeek randomDay = allDays[rand.nextInt(allDays.length)];
            if (!classDays.contains(randomDay)) {
                classDays.add(randomDay);
            }
        }

        return classDays;
    }

    private static TimeRange generateTimeRange(){

        int startHour = rand.nextInt(7) + 13; // Start between 7 AM and 8 PM
        int startMinute = rand.nextInt(60);  // Random minute between 0 and 59
        LocalTime startTime = LocalTime.of(startHour, startMinute);
        LocalTime endTime = startTime.plusHours(rand.nextInt(1, 4)); // Duration 1 to 3 hours

        return new TimeRange(startTime, endTime);
    }

    private static TimeSegments getTimeSegment(LocalTime startTime){
        return TimeSegments.getSegmentForTime(startTime);
    }
}
