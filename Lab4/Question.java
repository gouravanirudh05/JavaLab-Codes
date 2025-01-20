import java.util.*;

// Question class to hold question details
public class Question {
    private String questionID;//Unique id of the question
    private String question;// question
    private String answer;// answer
    private int points;// points for the quiz question
    private List<String>options;// List of options for the quiz question
    public Question(String questionID, String question, String answer, int points, List<String> options) {
        this.questionID = questionID;
        this.question = question;
        this.answer = answer;
        this.points = points;
        this.options = options;
    }
    public Question()
    {
        options = new ArrayList<>();
        points = 0;
        answer = "";
        questionID = "";
        question = "";
    }
    //Getter and setter methods for all the attributes
    public String getQuestionID() {
        return questionID;
    }
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public List<String> getOptions() {
        return options;
    }
    public void setOptions(List<String> options) {
        this.options = options;
    }
    

}
