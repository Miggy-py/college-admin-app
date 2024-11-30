package collegeapp.perezrojocollegeappfinal.model;

public enum Settings {
    MAX_COURSE_SIZE(200),
    MAX_SECTION_SIZE(1000),
    MAX_COLLEGE_SIZE(30000),
    LOWER_CRN(10000),
    MAX_CRN(100000),
    MAX_INSTRUCTORS(5000),
    MAX_TEXTBOOKS_PER_SECTION(2),
    MAX_CLASSROOM_SEATS(49),
    MAX_SECTIONS_PER_STUDENT(5),
    MAX_PROFESSORS_PER_COURSE(10);

    private int size;

    Settings(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
