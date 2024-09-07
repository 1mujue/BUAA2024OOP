package enums;

import commands.*;

/**
 * &#064;Classname COMMAND
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:54
 * &#064;Created MuJue
 */
public enum COMMAND {
    QUIT("quit", new QuitCommand()),
    REGISTER("register", new RegisterCommand()),
    LOGIN("login", new LoginCommand()),
    LOGOUT("logout", new LogoutCommand()),
    PRINT_INFO("printInfo", new PrintInfoCommand()),
    CREATE_COURSE("createCourse", new CreateCourseCommand()),
    LIST_COURSE("listCourse", new ListCourseCommand()),
    SELECT_COURSE("selectCourse", new SelectCourseCommand()),
    CANCEL_COURSE("cancelCourse", new CancelCourseCommand()),
    REMOVE_STUDENT("removeStudent", new RemoveStudentCommand()),
    SWITCH("switch", new SwitchCommand()),
    INPUT_COURSE_BATCH("inputCourseBatch", new InputCourseBatchCommand()),
    OUTPUT_COURSE_BATCH("outputCourseBatch", new OutputCourseBatchCommand()),
    LIST_COURSE_SCHEDULE("listCourseSchedule", new ListScheduleCommand()),
    LIST_STUDENT("listStudent",new ListStudentCommand()),
    UPLOAD_COURSE_SCHEDULE("uploadCourseSchedule", new UploadCourseScheduleCommand()),
    OPEN_FILE("openFile", new OpenFileCommand());
    private final String name;
    private final BaseCommand command;
    COMMAND(String name, BaseCommand command){
        this.name = name;
        this.command = command;
    }

    public BaseCommand getCommand() {
        return command;
    }

    public static COMMAND getInstance(String name){
        for(COMMAND command : values()){
            if(command.name.equals(name)){
                return command;
            }
        }
        return null;
    }
}
