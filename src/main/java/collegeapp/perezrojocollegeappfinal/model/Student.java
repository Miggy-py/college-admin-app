package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {
    private Name name;
    private String id;
    private Major major;
    private double gpa;
    private double creditsCompleted;
    private SectionsContainer currentSections;

    public Student(Name name, Major major, double gpa, double creditsCompleted, SectionsContainer currentSections) {
        this.name = name;
        this.id = generateID();
        this.major = major;
        this.gpa = gpa;
        this.creditsCompleted = creditsCompleted;
        this. currentSections = currentSections;
    }

    private String generateID() {
        return "" + DataCenter.getInstance().getStudentIDBase();
    }

    /*
    Again the student base for the IDs has to be stored in DC to have it unique across majors
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", id='" + id + '\'' +
                ", major=" + major +
                ", gpa=" + gpa +
                ", creditsCompleted=" + creditsCompleted +
                ", currentSections=" + currentSections +
                '}';
    }

     */

    @Override
    public int compareTo(Student o) {
        return 0;
    }


}
