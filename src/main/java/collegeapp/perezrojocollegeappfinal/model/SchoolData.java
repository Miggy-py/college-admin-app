package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.config.SchoolSettings;

import java.io.Serializable;
import java.util.*;

public class SchoolData implements Serializable {
    private CourseContainer courseContainer;
    private SectionsContainer sectionContainer;
    private StudentsEnrolled studentsEnrolled;
    private InstructorContainer instructorContainer;
    private PriorityQueue<Instructor> instructorPriorityQueue;

    public SchoolData() {
        courseContainer = new CourseContainer();
        sectionContainer = new SectionsContainer();
        studentsEnrolled = new StudentsEnrolled();
        instructorContainer = new InstructorContainer(SchoolSettings.MAX_INSTRUCTORS.getSize());
        instructorPriorityQueue = new PriorityQueue<>(SchoolSettings.MAX_INSTRUCTORS.getSize());
    }

    public CourseContainer getCourseContainer() {
        return courseContainer;
    }

    public SectionsContainer getSectionsContainer() {
        return sectionContainer;
    }

    public StudentsEnrolled getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public InstructorContainer getInstructorList() {
        return instructorContainer;
    }

    public void addCourse(Course course) {
        courseContainer.addCourse(course.getCourseNumber(), course);
    }

    public void addSection(Section section) {
        sectionContainer.addSection(section);
    }
}
