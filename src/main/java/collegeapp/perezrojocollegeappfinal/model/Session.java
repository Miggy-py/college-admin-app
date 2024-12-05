package collegeapp.perezrojocollegeappfinal.model;

public class Session {
    private static SchoolData currentSchoolData;

    public static void setCurrentSchoolData(SchoolData schoolData) {
        currentSchoolData = schoolData;
    }

    public static SchoolData getCurrentSchoolData() {
        return currentSchoolData;
    }

    public static void clearSession() {
        currentSchoolData = null;
    }
}

