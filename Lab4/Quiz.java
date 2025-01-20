import java.util.*;
//Quiz class 
public class Quiz {
    private String title;
    List<Question> questions;
    private String quizId;
    private int duration;
    public Quiz(String title, List<Question> questions, String quizId, int duration) {
        this.title = title;//Title of the quiz
        this.questions =new ArrayList<>();// List of questions in the quiz
        this.quizId = quizId;// Unique identifier for the quiz
        this.duration = duration;// Duration of the quiz 
    }
    // Getters and setters for all the methods
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public String getQuizId() {
        return quizId;
    }
    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void addQuestion(Question q)
    {
        questions.add(q);
    }
}
