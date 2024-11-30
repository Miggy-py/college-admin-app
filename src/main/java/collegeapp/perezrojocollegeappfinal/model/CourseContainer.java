package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.function.Predicate;

public class CourseContainer implements Serializable {
    private GenericBag<Course> courseBag;

    public CourseContainer() {
        courseBag = new GenericBag<>(Course.class, Settings.MAX_COURSE_SIZE.getSize());
    }

    public void addCourse(Course course) {
        courseBag.add(course);
    }

    /*
    Small reusability on this Predicate combination that will search through the courseBag using
    GenericBag's already made search method
     */
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

}

