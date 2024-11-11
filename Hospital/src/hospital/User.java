package hospital;

public class User {
    private String userID;
    private String password;
    private String name;
    private String gender;
    private UserRole role;

    public User(String userID, String password, String name, String gender, UserRole role) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.role = role;
    }

    // Getters and setters for user attributes
    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public UserRole getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
