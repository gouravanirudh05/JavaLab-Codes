import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// Helper class that manages users, quizzes, login status, and active quiz sessions
public class Helper {
    private HashMap<String, Users> users = new HashMap<>();//Stores user data by userID
    private HashMap<String, Quiz> quizzes = new HashMap<>(); // Stores quiz data by quizID
    private HashMap<String, Boolean> loginStatus = new HashMap<>(); // Tracks login status by userID
    private HashMap<String, QuizSession> quizActive = new HashMap<>();// Tracks active quiz sessions by userID
    private ExecutorService quizExecutor = Executors.newCachedThreadPool();// Thread pool for quiz sessions

    // Constructor to initialize the Helper class with users and quizzes
    public Helper(HashMap<String, Users> users, HashMap<String, Quiz> quizzes) {
        this.users = users;
        this.quizzes = quizzes;
    }

    // Method to validate an email format 
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Method to validate password strength
    public boolean isValidPassword(String password) {

        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    // Helper method to parse commands with quoted strings
    private String[] parseCommand(String command) {
        ArrayList<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(command);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1));
            } else {
                tokens.add(matcher.group(2));
            }
        }
        return tokens.toArray(new String[0]);
    }
// Method to process user commands in a loop
    public void processCommands() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] inputs = parseCommand(command);
            switch (inputs[0]) {
                case "REGISTER_STUDENT": {
                    String userID = inputs[1];
                    String username = inputs[2];
                    String email = inputs[3];
                    String password = inputs[4];
                    boolean flag = false;
                    // Check for duplicate userId
                    for (Users user : users.values()) {
                        if (user.getUserID().equals(userID)) {
                            System.out.println("Error: User ID " + userID + " already exists");
                            flag = true;
                        }
                    }
                    loginStatus.put(username, false);
                    // Validate email
                    if (!(isValidEmail(email))) {
                        System.out.println("Error: Invalid email format for " + email);
                        break;
                    }
                    // Validate password strength
                    if (!(isValidPassword(password))) {
                        System.out.println("Error: Weak password");
                        break;
                    }
                    // If no duplicate user ID is found
                    if (flag == false) {
                        Users user = new Student(username, userID, email, password);
                        users.put(userID, user);
                        System.out.println("Student Registered: " + user.getUserID() + " " + user.getUsername());
                    }
                    break;
                }
                case "REGISTER_ADMIN": {
                    String userID = inputs[1];
                    String username = inputs[2];
                    String email = inputs[3];
                    String password = inputs[4];
                    boolean flag = false;
                    // Check for duplicate userId
                    for (Users user : users.values()) {
                        if (user.getUserID().equals(userID)) {
                            System.out.println("Error: User ID " + userID + " already exists");
                            flag = true;
                            break;
                        }
                    }
                    loginStatus.put(userID, false);
                    // Validate email
                    if (!(isValidEmail(email))) {
                        System.out.println("Error: Invalid email format for " + email);
                        break;
                    }
                    // Validate password strength
                    if (!(isValidPassword(password))) {
                        System.out.println("Error: Weak password");
                        break;
                    }
                    // If no duplicate user ID is found
                    if (flag == false) {
                        Users user = new Admin(username, userID, email, password);
                        users.put(userID, user);
                        loginStatus.put(userID, false);
                        System.out.println("Admin Registered: " + user.getUserID() + " " + user.getUsername());
                    }
                    break;
                }
                case "LOGIN": {
                    String userID = inputs[1];
                    String password = inputs[2];
                    Users user = users.get(userID);
                    if (user != null && user.getPassword().equals(password)) {
                        boolean isLoggedIn = loginStatus.getOrDefault(userID, false);
                        if (!isLoggedIn) {
                            loginStatus.put(userID, true); // Update login status
                            System.out.println("User " + user.getUserID() + " logged in");
                        } else {
                            System.out.println("Error: User " + user.getUserID() + " is already logged in");
                        }
                    } else {
                        System.out.println("Error: Invalid userId or password");
                    }
                    break;
                }
                case "LOGOUT": {
                    String userID = inputs[1];
                    Users user = users.get(userID);
                    if (user != null) {
                        boolean isLoggedIn = loginStatus.getOrDefault(userID, false);
                        if (isLoggedIn) {
                            loginStatus.put(userID, false); // Update login status
                            System.out.println("User " + user.getUserID() + " logged out");
                        } else {
                            System.out.println("Error: User " + user.getUserID() + " is not logged in");
                        }
                    } else {
                        System.out.println("Error: Invalid userID");
                    }
                    break;
                }
                case "CREATE_MCQ_QUIZ": {
                    String userID = inputs[1];
                    if (users.get(userID).getRole().equals("Admin")) {
                        String quizId = inputs[2];
                        String title = inputs[3];
                        boolean flag = false;
                        int duration = Integer.parseInt(inputs[4]);
                        if (quizzes.get(quizId) != null) {
                            System.out.println("Error: Quiz ID " + quizId + "already exists ");
                            flag = true;
                        }
                        if (flag == false) {
                            Quiz quiz = new Quiz(title, null, quizId, duration);
                            quizzes.put(quizId, quiz);
                            System.out.println("MCQ Quiz Created: " + quiz.getQuizId() + " " + quiz.getTitle());
                        }
                    } else {
                        System.out.println(" Error: Access Denied for user " + userID);
                    }
                    break;
                }
                case "ADD_MCQ_QUESTION": {
                    String userID = inputs[1];
                    String quizID = inputs[2];
                    String questionID = inputs[3];
                    String question = inputs[4];
                    if (users.get(userID).getRole().equals("Admin")) {
                        Quiz quiz = quizzes.get(quizID);
                        if (quiz == null) {
                            System.out.println("Error: Quiz ID " + quizID + " does not exist");
                            break;
                        }
                        // Check if the question ID already exists in the quiz
                        boolean questionExists = false;
                        for (Question ques : quiz.getQuestions()) {
                            if (ques.getQuestionID().equals(questionID)) {
                                System.out.println(
                                        "Error: Question ID " + questionID + " already exists in quiz " + quizID);
                                questionExists = true;
                                break;
                            }
                        }
                        if (questionExists) {
                            break; // Exit if the question already exists
                        }
                        int points=Integer.parseInt(inputs[5]);

                        // Extract options from the input
                        String[] temp = inputs[6].split(";");
                        List<String> options = new ArrayList<>();
                        for (int i = 0; i < temp.length; i++) {
                            options.add(temp[i]);
                        }

                        // Ensure options are valid
                        if (options.size() < 2) {
                            System.out.println("Error: At least two options are required for an MCQ");
                            break;
                        }

                        // Extract and validate the correct answer
                        String answer = inputs[inputs.length - 1];
                        if (!options.contains(answer)) {
                            System.out.println("Error: Correct answer must be one of the provided options");
                            break;
                        }

                        // Add the new question to the quiz
                        Question q = new Question(questionID, question, answer, points, options);
                        quiz.addQuestion(q);
                        System.out.println("MCQ Question Added: " + questionID);
                    } else {
                        System.out.println(" Error: Access Denied for user " + userID);
                    }
                    break;
                }

                case "START_QUIZ": { 
                    String userId = inputs[1];
                    String quizId = inputs[2];
                    // Validate user and quiz
                    if (!loginStatus.getOrDefault(userId, false)) {
                        System.out.println("Error: User " + userId + " is not logged in");
                        break;
                    }
                    Users user = users.get(userId);
                    if (!user.getRole().equals("Student")) {
                        System.out.println("Error: Only students can start quizzes");
                        break;
                    }
                    Quiz quiz = quizzes.get(quizId);
                    if (quiz == null) {
                        System.out.println("Error: Quiz ID " + quizId + " does not exist");
                        break;
                    }

                    // Check if the user has an ongoing session
                    if (quizActive.containsKey(userId)) {
                        System.out.println("Error: Quiz already in progress for user " + userId);
                        break;
                    }

                    // Start the quiz in a new thread
                    QuizSession session = new QuizSession(userId, quiz);
                    quizActive.put(userId, session);
                    quizExecutor.submit(session);
                    System.out.println("Quiz Started: " + quiz.getQuizId() + " " + quiz.getTitle()+" by "+userId);
                    break;
                }
                case "ANSWER_QUESTION": {
                    String userId = inputs[1];
                    String quizId = inputs[2];
                    String questionId = inputs[3];
                    String answer = inputs[4];

                    // Validate the active session
                    QuizSession session = quizActive.get(userId);
                    if (session == null || !session.getQuizId().equals(quizId)) {
                        System.out.println("Error: No active quiz session for user " + userId);
                        break;
                    }

                    session.submitAnswer(questionId, answer);
                    break;
                }
                case "VIEW_SCORE": {
                    String userId = inputs[1];
                    String quizId = inputs[2];

                    QuizSession session = quizActive.get(userId);
                    if (session == null || !session.getQuizId().equals(quizId)) {
                        System.out.println("Error: No active quiz session for user " + userId);
                        break;
                    }

                    System.out.println("Score for " + userId + " in " + quizId + ": " + session.getScore());
                    break;
                }
                case "EXIT": {
                    scanner.close();
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Error: Invalid command format");
                }
            }
        }

    }
}
