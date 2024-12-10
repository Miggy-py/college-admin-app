package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Instructor implements Serializable {
    private String id;
    private Name name;
    private Major majorTaught;
    private LocalDate hireDate;
    private int creditsTeaching;
    private HashSet<String> preferredCRNs;
    private EnumSet<DaysOfWeek> preferredDays;
    private EnumSet<TimeSegments> preferredTimeSegments;

    public Instructor(Name name, Major majorTaught, LocalDate hireDate) {
        this.name = name;
        this.majorTaught = majorTaught;
        this.id = generateID();
        this.creditsTeaching = 0;
        this.hireDate = hireDate;
        this.preferredCRNs = new HashSet<>();
        this.preferredDays = EnumSet.noneOf(DaysOfWeek.class);
        this.preferredTimeSegments = EnumSet.noneOf(TimeSegments.class);
    }

    public int getCreditsTeaching() {
        return creditsTeaching;
    }

    public String getCreditsAsString(){
        return String.valueOf(creditsTeaching);
    }

    public void setCredits(int value){
        this.creditsTeaching = value;
    }

    public void addToCreditsTeaching(int credits) {
        this.creditsTeaching += credits;
    }

    public void removeCredits(int credits) {
        this.creditsTeaching -= credits;
    }

    public Major getMajorTaught() {
        return majorTaught;
    }

    public String getMajorTaughtAsString(){
        return majorTaught.getCourseName();
    }

    public void setMajorTaught(Major majorTaught) {
        this.majorTaught = majorTaught;
    }

    public Name getName() {
        return name;
    }

    public String getNameAsString(){
        return name.getFirstName() + " " + name.getLastName();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getHireDateAsString(){
        return hireDate.toString();
    }

    private String generateID() {
        return "" + DataCenter.getInstance().getInstructorIDBase();
    }

    public Set<String> getPreferredCRNs() {
        return preferredCRNs;
    }

    public String getPreferredCRNsAsString(){
        return preferredCRNs.toString();
    }

    public void setPreferredCRNs(HashSet<String> preferredCRNs) {
        this.preferredCRNs = preferredCRNs;
    }

    public EnumSet<DaysOfWeek> getPreferredDays() {
        return preferredDays;
    }

    public String getPreferredDaysAsString(){
        return preferredDays.toString();
    }

    public void setPreferredDays(EnumSet<DaysOfWeek> preferredDays) {
        this.preferredDays = preferredDays;
    }

    public EnumSet<TimeSegments> getPreferredTimeSegments() {
        return preferredTimeSegments;
    }

    public String getPreferredTimeSegmentsAsString(){
        return preferredTimeSegments.toString();
    }

    public void setPreferredTimeSegments(EnumSet<TimeSegments> preferredTimeSegments) {
        this.preferredTimeSegments = preferredTimeSegments;
    }

    /*
    At first the IDs weren't unique across majors, problem fix was to make the bases be stored in the DataCenter
    Singleton instead of in the class
     */

    @Override
    public String toString() {
        return id + " | " + name.getFirstName() + " " + name.getLastName();
    }

    /*
    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", hire date=" + hireDate +
                ", name=" + name +
                ", majorTaught=" + majorTaught +
                '}';
    }

     */

}
