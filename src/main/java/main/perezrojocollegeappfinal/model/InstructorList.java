package main.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.Random;

public class InstructorList implements Serializable{
    private GenericBag<Instructor> instructors;
    private int numberOfInstructors;

    public InstructorList() {
        instructors = new GenericBag<>(Instructor.class, Settings.MAX_INSTRUCTORS.getSize());
    }

    public GenericBag<Instructor> getInstructors() {
        return instructors;
    }

    public Instructor getRandomInstructor(Major m) {
        Random rand = new Random();
        Instructor[] pickedInstructors = instructors.search(Instructor.class, instructor -> instructor.getMajorTaught().equals(m));

        return  pickedInstructors[rand.nextInt(pickedInstructors.length)];
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        numberOfInstructors++;
    }

    public void displayInstructorList() {
        instructors.display();
    }

    public int getNumOfInstructors() {
        return numberOfInstructors;
    }

    public int getNumOfInstructorsMajor(Major m) {
        Instructor[] instructorsOfMajor = instructors.search(Instructor.class, instructor -> instructor.getMajorTaught().equals(m));

        return instructorsOfMajor.length;
    }

    public Instructor[] fireInstructor(String id) {
        return instructors.removeCopy(Instructor.class, instructor -> instructor.getId().equals(id));
    }
}
