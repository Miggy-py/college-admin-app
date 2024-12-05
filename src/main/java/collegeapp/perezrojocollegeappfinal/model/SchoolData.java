package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.config.SchoolSettings;

import java.util.*;

public class SchoolData {
    private TreeMap<String, Course> courseContainer;
    private TreeSet<Section> sectionContainer;
    private TreeMap<String, Student> studentsEnrolled;
    private HashMap<String, Instructor> instructorList;
    private PriorityQueue<Instructor> instructorPriorityQueue;

    public SchoolData() {
        courseContainer = new TreeMap<>();
        sectionContainer = new TreeSet<>();
        studentsEnrolled = new TreeMap<>();
        instructorList = new HashMap<>(SchoolSettings.MAX_INSTRUCTORS.getSize());
        instructorPriorityQueue = new PriorityQueue<>(SchoolSettings.MAX_INSTRUCTORS.getSize());
    }

    public TreeMap<String, Course> getCourseContainer() {
        return courseContainer;
    }

    public TreeSet<Section> getSectionsContainer() {
        return sectionContainer;
    }

    public TreeMap<String, Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public HashMap<String, Instructor> getInstructorList() {
        return instructorList;
    }
}
