package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Section implements Comparable<Section>, Serializable {
    private TimeRange timeRange;
    private TimeSegments timeSegment;
    private ArrayList<DayOfWeek> classDays;
    private String crn; // unique id for the section
    private Instructor instructor;
    private TextbookContainer bookList;
    private Major major;
    private String courseNumber;
    private Classroom classroom;
    private boolean isOnline;
    private ArrayList<String> studentIDs;

    public Section(TimeRange timeRange, TimeSegments timeSegment,ArrayList<DayOfWeek> classDays, String crn,
                   Instructor instructor, TextbookContainer bookList, Major major,
                   String courseNumber, Classroom classroom, boolean isOnline,
                   ArrayList<String> studentIDs) {
        this.timeRange = timeRange;
        this.timeSegment = timeSegment;
        this.classDays = classDays;
        this.crn = crn;
        this.instructor = instructor;
        this.bookList = bookList;
        this.major = major;
        this.courseNumber = courseNumber;
        this.classroom = classroom;
        this.isOnline = isOnline;
        this.studentIDs = studentIDs;
    }

    public TimeSegments getTimeSegment() {
        return timeSegment;
    }

    public void setTimeSegment(TimeSegments timeSegment) {
        this.timeSegment = timeSegment;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    public ArrayList<DayOfWeek> getClassDays() {
        return classDays;
    }

    public void setClassDays(ArrayList<DayOfWeek> classDays) {
        this.classDays = classDays;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public TextbookContainer getBookList() {
        return bookList;
    }

    public void setBookList(TextbookContainer bookList) {
        this.bookList = bookList;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public ArrayList<String> getStudentIDs() {
        return studentIDs;
    }

    public void addStudentId(String studentId) {
        this.studentIDs.add(studentId);
    }

    @Override
    public String toString() {
        return "Section{" +
                "timeRange=" + timeRange +
                ", timeSegment=" + timeSegment +
                ", classDays=" + classDays +
                ", crn='" + crn + '\'' +
                ", instructor=" + instructor +
                ", bookList=" + bookList +
                ", major=" + major +
                ", courseNumber='" + courseNumber + '\'' +
                ", classroom=" + classroom +
                ", isOnline=" + isOnline +
                ", studentIDs=" + studentIDs +
                '}';
    }

    @Override
    public int compareTo(Section o) {
        return crn.compareTo(o.getCrn());
    }
}
