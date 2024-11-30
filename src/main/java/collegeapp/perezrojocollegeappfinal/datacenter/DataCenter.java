package collegeapp.perezrojocollegeappfinal.datacenter;

import collegeapp.perezrojocollegeappfinal.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataCenter implements Serializable{
    private static DataCenter instance = null;
    private CourseContainer courses;
    private SectionsContainer sections;
    private StudentsEnrolled studentsEnrolled;
    private InstructorList instructorList;
    private ArrayList<String> firstNames;
    private ArrayList<String> lastNames;
    // making ID this rigid is bad, find a way to make a general ID for any Class
    private int instructorBase = 5000000;
    private int studentBase = 1000000;

    private DataCenter() {
        courses = new CourseContainer();
        sections = new SectionsContainer();
        studentsEnrolled = new StudentsEnrolled();
        instructorList = new InstructorList();
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();
        loadNames();
    }

    public int getInstructorBase() {
        return ++instructorBase;
    }

    public int getStudentBase() {
        return ++studentBase;
    }

    public void viewCourses(){
        courses.displayCourses();
    }

    public void viewStudents(){
        studentsEnrolled.display();
    }

    public void viewInstructors(){
        instructorList.displayInstructorList();
    }

    public StudentsEnrolled getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public CourseContainer getCourses() {
        return courses;
    }

    public SectionsContainer getSectionsContainer(){
        return sections;
    }

    public String addCourse(Course course) {
        boolean exists = courseExistsAlready(course.getCourseTitle(), course.getCourseNumber());
        if(exists){
            return "Course already exists";
        }

        courses.addCourse(course);
        return "Course added";
    }

    public void hireInstructor(Instructor instructor) {
        instructorList.addInstructor(instructor);
    }

    public void addSection(Section section) {
        sections.addSection(section);
    }

    public void enrollStudent(Student student) {
        studentsEnrolled.enroll(student);
    }

    public InstructorList getInstructorList(){
        return instructorList;
    }

    public boolean courseExistsAlready(String courseTitle, String courseNumber) {
        Course[] course = courses.findCourse(courseTitle, courseNumber);
        if(course.length == 0) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getFirstNames() {
        return firstNames;
    }

    public ArrayList<String> getLastNames() {
        return lastNames;
    }

    private void loadNames() {
        try (Scanner scan = new Scanner(new File("first-names.txt"))) {
            while (scan.hasNextLine()) {
                firstNames.add(scan.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Scanner scan = new Scanner(new File("last-names.txt"))) {
            while (scan.hasNextLine()) {
                lastNames.add(scan.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataCenter getInstance() {
        if (instance == null) {
            instance = load();
            if (instance == null) {
                instance = new DataCenter();
            }
        }

        return instance;
    }

    public static DataCenter load() {
        DataCenter inst = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("collegeData.dat"))) {
            inst = (DataCenter)ois.readObject();
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return inst;
    }

    public boolean save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("collegeData.dat"))) {
            oos.writeObject(instance);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
