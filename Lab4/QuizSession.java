import java.util.*;
class QuizSession implements Runnable {
    private String userId;
    private Quiz quiz;
    private HashMap<String, String> answers = new HashMap<>();
    private int score = 0;
    private boolean isActive = true;

    public QuizSession(String userId, Quiz quiz) {
        this.userId = userId;
        this.quiz = quiz;
    }

    public String getQuizId() {
        return quiz.getQuizId();
    }

    public int getScore() {
        return score;
    }

    public void submitAnswer(String questionId, String answer) {
        if (!isActive) {
            System.out.println("Error: Quiz session has ended for user " + userId);
            return;
        }
        for (Question question : quiz.getQuestions()) {
            if (question.getQuestionID().equals(questionId)) {
                if (!answers.containsKey(questionId)) {
                    answers.put(questionId, answer);
                    if (answer.equals(question.getAnswer())) {
                        score += question.getPoints();
                    }
                    System.out.println("Answer Submitted for " + questionId +" by "+userId);
                } else {
                    System.out.println("Error: Invalid Question ID");
                }
                return;
            }
        }
        System.out.println("Error: Invalid Question ID");
    }
// Method executed when the quiz session is started in a new thread
    @Override
    public void run() {
        try {
            Thread.sleep(quiz.getDuration() * 60 * 1000);
        } catch (InterruptedException e) {

        } finally {
            isActive = false;// Mark the session as inactive
            System.out.println("Quiz Ended for " + userId);
        }
    }
}
