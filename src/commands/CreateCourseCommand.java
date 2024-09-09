package commands;

import entity.Course;
import entity.tokens.*;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.CourseExecutor;
import utils.Outputer;
import validators.*;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname CreateCourseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:31
 * &#064;Created MuJue
 */
public class CreateCourseCommand extends BaseCommand{
    private CourseName courseName;
    private CourseScheduleTime courseScheduleTime;
    private CourseCredit courseCredit;
    private CourseDurationTime courseDurationTime;
    @Override
    public void execute() throws ExecutionException {
        Course course = new Course(
                courseName.getValue(),
                courseScheduleTime.getWeekTime(),
                courseScheduleTime.getFromTime(),
                courseScheduleTime.getToTime(),
                courseCredit.getValue(),
                courseDurationTime.getValue()
        );
        CourseExecutor courseExecutor = CourseExecutor.getInstance();
        String message = courseExecutor.createCourse(course);
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "createCourse");

        courseName = new CourseName(parameters.get(0));
        courseScheduleTime = new CourseScheduleTime(parameters.get(1));
        courseCredit = new CourseCredit(Double.parseDouble(parameters.get(2)));
        courseDurationTime = new CourseDurationTime(Integer.parseInt(parameters.get(3)));

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        List<String> permissions = new ArrayList<>();
        permissions.add("Teacher");
        permissionValidator.legalityValidate(permissions);

        CourseValidator courseValidator = CourseValidator.getInstance();
        courseValidator.isCourseNumberReachLimit();
        courseValidator.courseTokenValidate(courseName);
        courseValidator.isCourseNameExist(courseName.getValue());
        courseValidator.courseTokenValidate(courseScheduleTime);
        courseValidator.courseTokenValidate(courseCredit);
        courseValidator.courseTokenValidate(courseDurationTime);
    }
}
