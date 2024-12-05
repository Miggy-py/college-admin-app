package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.TreeMap;

@Deprecated
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


    /*

    public StudentsEnrolled() {
        students = new GenericBag<>(Student.class, SchoolSettings.MAX_COLLEGE_SIZE.getSize());
    }

    public void enroll(Student student) {
        students.add(student);
    }

    public void display(){
        students.display();
    }

    public Student[] expelStudent(String id){
        return students.removeCopy(Student.class, student -> student.getId().equals(id));
    }

     */
}
