package entity;

/**
 * &#064;Classname TeacherCourse
 * &#064;Description  TODO
 * &#064;Date 2024/9/15 16:55
 * &#064;Created MuJue
 */
public class TeacherCourse {
    private final String tName;
    private final Course course;

    public String gettName() {
        return tName;
    }

    public Course getCourse() {
        return course;
    }

    public TeacherCourse(String tName, Course course) {
        this.tName = tName;
        this.course = course;
    }
    @Override
    public String toString() {
        return tName + ' ' + course.toString();
    }
}
