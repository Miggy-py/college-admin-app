package collegeapp.perezrojocollegeappfinal.config;

public enum SchoolSettings {
    MAX_COURSE_SIZE(5),
    MAX_CLASS_DAYS(2),
    // MAX_SECTION_SIZE(1000),
    // MAX_COLLEGE_SIZE(30000),
    LOWER_CRN(10000),
    MAX_CRN(100000),
    MAX_TEXTBOOKS_PER_SECTION(2),
    MAX_CLASSROOM_SEATS(49),
    MAX_SECTIONS_PER_STUDENT(5),
    STUDENT_ID_BASE(1000000),
    // SCHOOL_ID_BASE(1),

    INSTRUCTOR_ID_BASE(10000),
    MAX_INSTRUCTORS_PER_COURSE(4),
    MAX_INSTRUCTORS(10),
    INSTRUCTOR_MIN_CREDITS(15),
    INSTRUCTOR_MAX_CREDITS(19);

    private int size;

    SchoolSettings(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
