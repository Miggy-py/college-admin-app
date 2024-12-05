package collegeapp.perezrojocollegeappfinal.model;

public enum DayOfWeek {
    SUN("Sunday"), MON("Monday"), TUE("Tuesday"), WED("Wednesday"), THU("Thursday"), FRI("Friday"), SAT("Saturday");

    private String dayOfWeek;

    DayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String get() {
        return dayOfWeek;
    }
}
