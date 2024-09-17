package enums;

import commands.*;

/**
 * &#064;Classname COMMAND
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:54
 * &#064;Created MuJue
 */
public enum COMMAND {
    QUIT("quit", QuitCommand.getInstance()),
    REGISTER("register", RegisterCommand.getInstance()),
    LOGIN("login", LoginCommand.getInstance()),
    LOGOUT("logout", LogoutCommand.getInstance()),
    PRINT_INFO("printInfo", PrintInfoCommand.getInstance()),
    CREATE_COURSE("createCourse", CreateCourseCommand.getInstance()),
    LIST_COURSE("listCourse", ListCourseCommand.getInstance()),
    SELECT_COURSE("selectCourse", SelectCourseCommand.getInstance()),
    CANCEL_COURSE("cancelCourse", CancelCourseCommand.getInstance()),
    REMOVE_STUDENT("removeStudent", RemoveStudentCommand.getInstance()),
    SWITCH("switch", SwitchCommand.getInstance()),
    INPUT_COURSE_BATCH("inputCourseBatch", InputCourseBatchCommand.getInstance()),
    OUTPUT_COURSE_BATCH("outputCourseBatch", OutputCourseBatchCommand.getInstance()),
    LIST_COURSE_SCHEDULE("listCourseSchedule", ListCourseScheduleCommand.getInstance()),
    LIST_STUDENT("listStudent",ListStudentCommand.getInstance()),
    UPLOAD_COURSE_SCHEDULE("uploadCourseSchedule", UploadCourseScheduleCommand.getInstance()),
    OPEN_FILE("openFile", OpenFileCommand.getInstance());
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
