package entity;

import java.time.temporal.WeekFields;

/**
 * &#064;Classname ScheduleCourse
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 14:06
 * &#064;Created MuJue
 */
public class ScheduleCourse {
    private Integer weekTime;
    private Integer fromTime;
    private Integer toTime;
    private String courseName;
    private Double credit;
    private Integer durationTime;
    private String teacherName;

    public ScheduleCourse(Integer weekTime, Integer fromTime, Integer toTime, String courseName, Double credit, Integer durationTime, String teacherName) {
        this.weekTime = weekTime;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.courseName = courseName;
        this.credit = credit;
        this.durationTime = durationTime;
        this.teacherName = teacherName;
    }

    public Integer getWeekTime() {
        return weekTime;
    }

    public Integer getFromTime() {
        return fromTime;
    }
    @Override
    public String toString() {
        return weekTime + "_" + fromTime + "-" + toTime + ' '
                + courseName + " "
                + credit + " "
                + durationTime + " "
                + teacherName + "\n";
    }
}
