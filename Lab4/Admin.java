public class Admin extends Users {
    public Admin(String username, String userID, String email, String password) {
        super(username, userID, email, password); // Calling the constructor of the Users class
    }

    //Method to get role
    public String getRole() {
        return "Admin";
    }
}