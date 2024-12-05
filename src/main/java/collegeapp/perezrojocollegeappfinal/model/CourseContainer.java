package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.TreeMap;

@Deprecated
public class CourseContainer implements Serializable {
    private TreeMap<String, Course> courseBag;

    public CourseContainer() {
        courseBag = new TreeMap<>();
    }

    public void addCourse(String courseID, Course course) {
        courseBag.put(courseID ,course);
    }

    public Course getCourse(String courseID) {
        return courseBag.get(courseID);
    }

    /*
    Small reusability on this Predicate combination that will search through the courseBag using
    GenericBag's already made search method
     */
    /*
    private Predicate<Course> createCoursePredicate(String courseTitle, String courseNumber) {
        Predicate<Course> namePredicate = course -> course.getCourseTitle().equals(courseTitle);
        Predicate<Course> numberPredicate = course -> course.getCourseNumber().equals(courseNumber);
        return namePredicate.and(numberPredicate);
    }


    public Course[] findCourse(String courseTitle, String courseNumber) {
        Predicate<Course> combinedPredicate = createCoursePredicate(courseTitle, courseNumber);
        return courseBag.search(Course.class, combinedPredicate);
    }



    public Course[] removeCourse(String courseTitle, String courseNumber) {
        Predicate<Course> combinedPredicate = createCoursePredicate(courseTitle, courseNumber);
        return courseBag.removeCopy(Course.class, combinedPredicate);
    }

    public void displayCourses() {
        courseBag.display();
    }

     */

}

