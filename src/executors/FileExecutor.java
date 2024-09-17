package executors;

import entity.Course;
import entity.tokens.Path;
import exceptions.ExecutionException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;
import utils.ScannerBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * &#064;Classname FileExecutor
 * &#064;Description  TODO
 * &#064;Date 2024/9/14 22:35
 * &#064;Created MuJue
 */
public class FileExecutor {
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final FileExecutor fileExecutor = new FileExecutor();
    private FileExecutor(){;}
    public static FileExecutor getInstance(){
        return fileExecutor;
    }
    public String outputTeacherCourse(String path) throws ExecutionException{
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new ExecutionException("Output object failed\n");
            }
        }
        try(
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(path)
                )
                ) {
            String tid = stateManipulator.getStateId();
            List<Integer> cids = userCourseManipulator.getTeacherCourse(tid);
            List<Course> courses = new ArrayList<>();
            for(Integer cid : cids){
                Course course = courseManipulator.getCourseById(cid);
                courses.add(course);
            }
            oos.writeObject(courses);
        }catch (IOException e) {
            throw new ExecutionException("Output object failed\n");
        }
        return "Output course batch success\n";
    }
    public String inputTeacherCourse(String path) throws ExecutionException{
        StringBuilder message = new StringBuilder();
        try(
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(path)
                )
                ){
            List<Course> courses = (List<Course>)ois.readObject();
            String tid = stateManipulator.getStateId();
            for(Course course : courses){
                String name = course.getName();
                int weekTime = course.getWeekTime();
                int fromTime = course.getFromTime();
                int toTime = course.getToTime();
                if(userCourseManipulator.getTeacherCourseNumber(tid) >= 10){
                    message.append("Course count reaches limit\n");
                    continue;
                }
                if(userCourseManipulator.isTeacherCourseNameExist(tid, name)){
                    message.append("Course name already exists\n");
                    continue;
                }
                if(userCourseManipulator.isTeacherCourseTimeConflict(tid, weekTime, fromTime, toTime)){
                    message.append("Course time conflicts\n");
                    continue;
                }
                course.setId();
                courseManipulator.addCourse(course);
                userCourseManipulator.createTeacherCourse(tid, course.getId());
                message.append("Create course success (courseId: C-").append(course.getId()).append(")\n");
            }
            message.append("Input course batch success\n");
        } catch(IOException | ClassNotFoundException e){
            throw new ExecutionException("File operation failed\n");
        }
        return message.toString();
    }
    public String openFile(Path filePath) throws ExecutionException{
        ScannerBuilder scannerBuilder = ScannerBuilder.getInstance();
        scannerBuilder.setPath(filePath.getValue());
        Scanner scanner = scannerBuilder.getScanner();
        StringBuilder stringBuilder = new StringBuilder();
        while(scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return stringBuilder.toString();
    }
}
