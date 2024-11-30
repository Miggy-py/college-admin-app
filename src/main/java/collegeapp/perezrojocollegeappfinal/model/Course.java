package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.Arrays;

public class Course implements Comparable<Course>, Serializable {
    private String courseTitle;
    private String courseNumber;
    private String description;
    private double credits;
    private SectionsContainer sectionList;

    public Course(String courseTitle, String courseNumber, String description, double credits, SectionsContainer sectionList) {
        this.courseTitle = courseTitle;
        this.courseNumber = courseNumber;
        this.description = description;
        this.credits = credits;
        this.sectionList = sectionList;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public SectionsContainer getSectionList() {
        return sectionList;
    }

    public void setSectionList(SectionsContainer sectionList) {
        this.sectionList = sectionList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseTitle='" + courseTitle + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                ", sectionList=" + Arrays.toString(sectionList.getAllSections()) +
                '}';
    }

    /*
    First compare by course title, then compare by course number
     */

    @Override
    public int compareTo(Course o) {
        if(!courseTitle.equals(o.getCourseTitle())) {
            return courseTitle.compareTo(o.getCourseTitle());
        }
        else if(!courseNumber.equals(o.getCourseNumber())) {
            return courseNumber.compareTo(o.getCourseNumber());
        }
        return 0;
    }
}
