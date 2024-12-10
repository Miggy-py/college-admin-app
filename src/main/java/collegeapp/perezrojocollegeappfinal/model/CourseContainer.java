package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.TreeMap;

public class CourseContainer implements Serializable {
    // TreeMap for unknown sizes and lookup by the number assigned to a course
    private TreeMap<String, Course> courseBag;

    public CourseContainer() {
        courseBag = new TreeMap<>();
    }

    public void addCourse(String courseNumber, Course course) {
        courseBag.put(courseNumber ,course);
    }

    public Course getCourse(String courseID) {
        return courseBag.get(courseID);
    }

    public int getAmountOfCourse(){
        return courseBag.size();
    }

    @Override
    public String toString() {
        return "CourseContainer{" +
                "courseBag=" + courseBag +
                '}';
    }
}

