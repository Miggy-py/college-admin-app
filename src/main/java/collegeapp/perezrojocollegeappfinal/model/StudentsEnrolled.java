package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.TreeMap;

public class StudentsEnrolled implements Serializable {
    private TreeMap<String, Student> students;

    public StudentsEnrolled() {
        students = new TreeMap<>();
    }

    public void enroll(String studentId, Student student) {
        students.put(studentId, student);
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

}
