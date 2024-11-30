package collegeapp.perezrojocollegeappfinal.model;

import collegeapp.perezrojocollegeappfinal.datacenter.DataCenter;

import java.io.Serializable;

public class Instructor implements Comparable<Instructor>, Serializable {
    private String id;
    private Name name;
    private Major majorTaught;

    public Instructor(Name name, Major majorTaught) {
        this.name = name;
        this.majorTaught = majorTaught;
        this.id = generateID();
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

    /*
    At first the IDs weren't unique across majors, problem fix was to make the bases be stored in the DataCenter
    Singleton instead of in the class
     */
    private String generateID() {
        return "" + DataCenter.getInstance().getInstructorBase();
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", majorTaught=" + majorTaught +
                '}';
    }

    @Override
    public int compareTo(Instructor o) {
        return id.compareTo(o.id);
    }
}
