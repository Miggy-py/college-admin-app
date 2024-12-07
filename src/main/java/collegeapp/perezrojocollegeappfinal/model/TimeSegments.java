package collegeapp.perezrojocollegeappfinal.model;

import java.io.Serializable;
import java.time.LocalTime;

public enum TimeSegments implements Serializable {
    EARLY_MORNING(LocalTime.of(7, 0), LocalTime.of(9, 0)),
    MORNING(LocalTime.of(9, 1), LocalTime.of(12, 0)),
    AFTERNOON(LocalTime.of(12, 1), LocalTime.of(16, 0)),
    LATE_AFTERNOON(LocalTime.of(16, 1), LocalTime.of(18, 0)),
    EVENING(LocalTime.of(18, 1), LocalTime.of(23, 59));

    private LocalTime startTime;
    private LocalTime endTime;

    TimeSegments(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isWithin(LocalTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }

    // Helps us find which TimeSegment is correct for the time of a class
    public static TimeSegments getSegmentForTime(LocalTime time) {
        for (TimeSegments segment : TimeSegments.values()) {
            if (segment.isWithin(time)) {
                return segment;
            }
        }
        return null;
    }
}
