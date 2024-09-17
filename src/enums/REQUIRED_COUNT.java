package enums;

/**
 * &#064;Classname REQUIRED_COUNT
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:53
 * &#064;Created MuJue
 */
public enum REQUIRED_COUNT {
    QUIT_COUNT(0, 0,"quit"),
    REGISTER_COUNT(5, 5,"register"),
    LOGIN_COUNT(2, 2,"login"),
    LOGOUT_COUNT(0, 1,"logout"),
    PRINT_INFO_COUNT(0, 1,"printInfo"),
    CREATE_COURSE_COUNT(4, 4,"createCourse"),
    LIST_COURSE_COUNT(0, 1,"listCourse"),
    SELECT_COURSE_COUNT(1, 1,"selectCourse"),
    CANCEL_COURSE_COUNT(1, 1,"cancelCourse"),
    SWITCH_COUNT(1, 1,"switch"),
    INPUT_COURSE_BATCH_COUNT(1, 1,"inputCourseBatch"),
    OUTPUT_COURSE_BATCH_COUNT(1, 1,"outputCourseBatch"),
    LIST_COURSE_SCHEDULE(0, 1,"listCourseSchedule"),
    LIST_STUDENT_COUNT(1, 1,"listStudent"),
    REMOVE_STUDENT_COUNT(1, 2,"removeStudent"),
    UPLOAD_COURSE_SCHEDULE_COUNT(1, 1,"uploadCourseSchedule"),
    OPEN_FILE_COUNT(1, 3,"openFile");
    private final int lowLimit;
    private final int highLimit;
    private final String commandName;

    REQUIRED_COUNT(int lowLimit, int highLimit, String commandName){
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.commandName = commandName;
    }

    public int getLowLimit() {
        return lowLimit;
    }

    public int getHighLimit() {
        return highLimit;
    }
    private String getCommandName(){
        return commandName;
    }
    public static REQUIRED_COUNT getParameterCount(String commandName){
        for(REQUIRED_COUNT COUNT : values()){
            if(COUNT.commandName.equals(commandName)){
                return COUNT;
            }
        }
        return null;
    }

}
