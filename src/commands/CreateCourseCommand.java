package commands;

import entity.Course;
import entity.tokens.*;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.CourseExecutor;
import utils.Outputer;
import validators.*;
import validators.courseValidators.CourseExistenceValidator;
import validators.courseValidators.CourseNameValidator;
import validators.courseValidators.CourseScheduleTimeValidator;
import validators.userValidators.UserPermissionValidator;

import java.util.List;

/**
 * &#064;Classname CreateCourseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:31
 * &#064;Created MuJue
 */
public class CreateCourseCommand extends BaseCommand{
    private CourseName courseName = null;
    private CourseScheduleTime courseScheduleTime = null;
    private CourseCredit courseCredit = null;
    private CourseDurationTime courseDurationTime = null;
    private final static CreateCourseCommand createCourseCommand = new CreateCourseCommand();
    private CreateCourseCommand (){;}
    public static CreateCourseCommand getInstance(){
        return createCourseCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        Course course = new Course(
                courseName.getValue(),
                courseScheduleTime.getWeekTime(),
                courseScheduleTime.getFromTime(),
                courseScheduleTime.getToTime(),
                courseCredit.getCredit(),
                courseDurationTime.getCourseDurationTime()
        );
        CourseExecutor courseExecutor = CourseExecutor.getInstance();
        String message = courseExecutor.createCourse(course);
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "createCourse");

        courseName = new CourseName(parameters.get(0));
        courseScheduleTime = new CourseScheduleTime(parameters.get(1));
        courseCredit = new CourseCredit(parameters.get(2));
        courseDurationTime = new CourseDurationTime(parameters.get(3));

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Teacher"));

        CourseExistenceValidator courseExistenceValidator = CourseExistenceValidator.getInstance();

        UserCourseValidator userCourseValidator = UserCourseValidator.getInstance();
        userCourseValidator.isCurrentTeacherCourseNumberReachLimit();

        courseExistenceValidator.tokenValidate(courseName);

        CourseNameValidator courseNameValidator = CourseNameValidator.getInstance();
        courseNameValidator.isCertainTeacherCourseNameExist(courseName);

        courseExistenceValidator.tokenValidate(courseScheduleTime);

        CourseScheduleTimeValidator courseScheduleTimeValidator = CourseScheduleTimeValidator.getInstance();
        courseScheduleTimeValidator.isTeacherCourseScheduleTimeConflict(courseScheduleTime);

        courseExistenceValidator.tokenValidate(courseCredit);
        courseExistenceValidator.tokenValidate(courseDurationTime);
    }
}
