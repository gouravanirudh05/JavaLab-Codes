import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Users>users = new HashMap<>();// Collection of all the users
        HashMap<String, Quiz>quizzes = new HashMap<>();// Collection of all the quiz instances
        Helper helper = new Helper(users, quizzes);
        helper.processCommands();

    }
}