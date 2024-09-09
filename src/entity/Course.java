package entity;

import executors.CourseExecutor;
import utils.TokenHandler;

import java.util.List;

/**
 * &#064;Classname Course
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 23:15
 * &#064;Created MuJue
 */
public class Course {
    private static int globalCourseId = 1;
    private final int id;
    private final String name;
    private final int weekTime;
    private final int fromTime;
    private final int toTime;
    private final double credit;
    private final int durationTime;

    public Course(String name, int weekTime, int fromTime, int toTime, double credit, int durationTime) {
        this.id = globalCourseId++;
        this.name = name;
        this.weekTime = weekTime;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.credit = credit;
        this.durationTime = durationTime;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeekTime() {
        return weekTime;
    }

    public int getFromTime() {
        return fromTime;
    }

    public int getToTime() {
        return toTime;
    }

    public double getCredit() {
        return credit;
    }

    public int getDurationTime() {
        return durationTime;
    }

    @Override
    public String toString() {
        return "C-" + id + " " +
                name + " " +
                weekTime + "_" +
                fromTime + "-" +
                toTime + " " +
                credit + " " +
                durationTime + "\n";
    }
}
