public class Student extends Users 
{   
    public Student(String username, String userID, String email, String password) {
        super(username, userID, email, password);
    }
    //Method to get the role of the user
    public String getRole()
    {
        return "Student";
    }

}
