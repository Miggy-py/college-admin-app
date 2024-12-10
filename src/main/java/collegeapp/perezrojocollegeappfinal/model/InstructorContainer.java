package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.*;

public class InstructorContainer implements Serializable{
    private LinkedHashMap<String, Instructor> instructors;

    public InstructorContainer(int maxSize) {
        instructors = new LinkedHashMap<>(maxSize);
    }

    public void addInstructor(String instructorID, Instructor instructor) {
        instructors.put(instructorID, instructor);
    }

    public LinkedHashMap<String, Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<String> getInstructorNames() {
        ArrayList<String> instructorsNames = new ArrayList<>();
        for (Instructor instructor : instructors.values()) {
            instructorsNames.add(instructor.getNameAsString());
        }
        return instructorsNames;
    }

    public Instructor getInstructor(String instructorID) {
        return instructors.get(instructorID);
    }

    public int getNumOfInstructorsMajor(Major major) {
        int counter = 0;
        for (Instructor instructor : instructors.values()) {
            if (instructor.getMajorTaught() == major){
                counter++;
            }
        }

        return counter;
    }
}
