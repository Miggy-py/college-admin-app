package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;

public enum DaysOfWeek implements Serializable {
    MON("Monday"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday");

    private String dayOfWeek;

    DaysOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String get() {
        return dayOfWeek;
    }
}
