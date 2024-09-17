package entity.tokens;

/**
 * &#064;Classname StudentSelectCourse
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 19:19
 * &#064;Created MuJue
 */
public class StudentSelectCourse {
    private UserId userId;
    private UserName userName;

    public StudentSelectCourse(String userId, String userName) {
        this.userId = new UserId(userId);
        this.userName = new UserName(userName);
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userId.getValue() + ": " + userName.getValue() + "\n";
    }
}
