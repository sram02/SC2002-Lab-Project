package hospital;

public abstract class User {
    protected String userID;
    protected String password;
    protected String name;
    protected String gender;
    protected UserRole role;

    public User(String userID, String password, String name, String gender, UserRole role) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.role = role;
    }

    public String getUserID() { return userID; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public UserRole getRole() { return role; }
    
    public String getPassword() { return password; }

    public boolean validateLogin(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password changed successfully.");
    }
}
