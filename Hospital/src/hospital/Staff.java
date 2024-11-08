package hospital;

public class Staff extends User {
    protected StaffRole role;
    protected int age;

    public Staff(String userID, String name, String gender, StaffRole role, int age) {
        super(userID, "password", name, gender, UserRole.STAFF);
        this.role = role;
        this.age = age;
    }

    // Add a specific getter for StaffRole
    public StaffRole getStaffRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
