package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;

public class StudentsEnrolled implements Serializable {
    private GenericBag<Student> students;

    public StudentsEnrolled() {
        students = new GenericBag<>(Student.class, Settings.MAX_COLLEGE_SIZE.getSize());
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
}
