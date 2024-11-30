package main.perezrojocollegeappfinal.model;

public enum Major {
    MATHEMATICS("MAT", Buildings.RI.getName()),
    COMPUTER_SCIENCE("CSE", Buildings.RI.getName()),
    BIOLOGY("BIO", Buildings.WJL.getName()),
    ENGLISH("ENG", Buildings.IS.getName()),
    ENGINEERING("ENS", Buildings.RI.getName()),
    HISTORY("HIS", Buildings.SO.getName()),
    PHYSICS("PHY", Buildings.SM.getName());

    private String courseName;
    private String asssociatedBuilding;

    Major(String courseName, String associatedBuilding){
        this.courseName = courseName;
        this.asssociatedBuilding = associatedBuilding;
    }

    public String getCourseName(){
        return courseName;
    }

    /*
    Storing the associated building with the major allows an easy way to make sure majors are in their correct building
    when automatically generating dummy Sections/Courses
     */
    public String getAsssociatedBuilding(){
        return asssociatedBuilding;
    }
}
