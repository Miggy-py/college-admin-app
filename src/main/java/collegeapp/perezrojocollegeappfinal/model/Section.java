package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;


public class Section implements Comparable<Section>, Serializable {
    private TimeRange timeRange;
    private TimeSegments timeSegment;
    private LinkedHashSet<DaysOfWeek> classDays;
    private String crn; // unique id for the section
    private Instructor instructor;
    private TextbookContainer bookList;
    private Major major;
    private String courseNumber;
    private Classroom classroom;
    private boolean isOnline;
    private TreeSet<String> studentIDs;
    private int credits;

    public Section(TimeRange timeRange, TimeSegments timeSegment, LinkedHashSet<DaysOfWeek> classDays, String crn,
                   Instructor instructor, TextbookContainer bookList, Major major,
                   String courseNumber, Classroom classroom, boolean isOnline,
                   TreeSet<String> studentIDs, int credits) {
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
        this.credits = credits;
    }

    public int getCredits(){
        return credits;
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

    public LinkedHashSet<DaysOfWeek> getClassDays() {
        return classDays;
    }

    public void setClassDays(LinkedHashSet<DaysOfWeek> classDays) {
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

    public TreeSet<String> getStudentIDs() {
        return studentIDs;
    }

    public void addStudentId(String studentId) {
        this.studentIDs.add(studentId);
    }

    public String toString(){
        return crn + " | " + major.getCourseName() + " " + courseNumber;
    }

    @Override
    public int compareTo(Section o) {
        return crn.compareTo(o.getCrn());
    }

    public String getCourseName() {
        return major.getCourseName() + " ";
    }

    public String getNumber() {
        return courseNumber;
    }

    public LocalTime getStartTime(){
        return timeRange.getStartTime();
    }

    public LocalTime getEndTime(){
        return timeRange.getEndTime();
    }

    public String getStartTimeAsString() {
        return timeRange.getStartTime().toString();
    }

    public String getEndTimeAsString() {
        return timeRange.getEndTime().toString();
    }

    public LinkedHashSet<DaysOfWeek> getDaysSet() {
        return classDays;
    }

    public String getDays() {
        return classDays.toString();
    }
}
