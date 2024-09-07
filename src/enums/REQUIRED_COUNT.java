package enums;

/**
 * &#064;Classname REQUIRED_COUNT
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:53
 * &#064;Created MuJue
 */
public enum REQUIRED_COUNT {
    QUIT_COUNT(0, 0),
    REGISTER_COUNT(5, 5),
    LOGIN_COUNT(2, 2),
    LOGOUT_COUNT(0, 1),
    PRINT_INFO_COUNT(0, 1),
    CREATE_COURSE_COUNT(4, 4),
    LIST_COURSE_COUNT(0, 1),
    SELECT_COURSE_COUNT(1, 1),
    CANCEL_COURSE_COUNT(1, 1),
    REMOVE_STUDENT_COUNT(1, 2),
    SWITCH_COUNT(1, 1),
    INPUT_COURSE_BATCH_COUNT(1, 1),
    OUTPUT_COURSE_BATCH_COUNT(1, 1),
    LIST_COURSE_SCHEDULE(0, 1),
    LIST_STUDENT_COUNT(1, 1),
    UPLOAD_COURSE_SCHEDULE_COUNT(1, 1),
    OPEN_FILE_COUNT(1, 3);
    private int lowLimit;
    private int highLimit;

    REQUIRED_COUNT(int lowLimit, int highLimit){
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
    }

    public int getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(int lowLimit) {
        this.lowLimit = lowLimit;
    }

    public int getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(int highLimit) {
        this.highLimit = highLimit;
    }
}
