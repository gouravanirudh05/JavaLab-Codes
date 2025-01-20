// Abstract class Users serves as a base class for different types of users
public abstract class Users {
    // Private fields for user details
    private  String username;// Stores the username of the user
    private String userID;// Unique identifier for the user
    private String email; // Email address of the user
    private String password="";// Password for the user, defaulted to an empty string
    public Users(String username, String userID, String email, String password) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.password = password;
    }
    // Constructor to initialize user details
    //Getter and setter methods for all the attributes
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //Abstract method to get the Role
    public abstract String getRole();
}
