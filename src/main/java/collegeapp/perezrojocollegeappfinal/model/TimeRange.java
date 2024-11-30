package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.time.LocalTime;

public class TimeRange implements Serializable {
    LocalTime startTime;
    LocalTime endTime;

    public TimeRange(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
