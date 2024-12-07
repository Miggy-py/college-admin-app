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

    public void addToCreditsTeaching(int credits) {
        this.creditsTeaching += credits;
    }

    public Major getMajorTaught() {
        return majorTaught;
    }

    public void setMajorTaught(Major majorTaught) {
        this.majorTaught = majorTaught;
    }

    public Name getName() {
        return name;
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

    private String generateID() {
        return "" + DataCenter.getInstance().getInstructorIDBase();
    }

    public Set<String> getPreferredCRNs() {
        return preferredCRNs;
    }

    public void setPreferredCRNs(HashSet<String> preferredCRNs) {
        this.preferredCRNs = preferredCRNs;
    }

    public EnumSet<DaysOfWeek> getPreferredDays() {
        return preferredDays;
    }

    public void setPreferredDays(EnumSet<DaysOfWeek> preferredDays) {
        this.preferredDays = preferredDays;
    }

    public EnumSet<TimeSegments> getPreferredTimeSegments() {
        return preferredTimeSegments;
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
        return "Instructor{" +
                "id='" + id + '\'' +
                ", hire date=" + hireDate +
                ", name=" + name +
                ", majorTaught=" + majorTaught +
                '}';
    }

}
