package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

@Deprecated
public class InstructorList implements Serializable{
    private HashMap<String, Instructor> instructors;
    private PriorityQueue<Instructor> instructorPriorityQueue;

    public InstructorList() {
        instructors = new HashMap<>();
        instructorPriorityQueue = new PriorityQueue<>(Comparator.comparing(Instructor::getHireDate));
    }

    public void addInstructor(String instructorID, Instructor instructor) {
        instructors.put(instructorID, instructor);
        instructorPriorityQueue.add(instructor);
    }

    public Instructor getInstructor(String instructorID) {
        return instructors.get(instructorID);
    }

    public PriorityQueue<Instructor> getInstructorPriorityQueue() {
        return instructorPriorityQueue;
    }
}

    /*
    public InstructorList() {
        instructors = new GenericBag<>(Instructor.class, SchoolSettings.MAX_INSTRUCTORS.getSize());
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

     */

