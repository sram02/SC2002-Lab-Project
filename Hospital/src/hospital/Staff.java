package hospital;

public class Staff extends User {
    private StaffRole role;
    private int age;

    public Staff(String userID, String password, String name, String gender, int age, StaffRole role) {
        super(userID, password, name, gender, UserRole.STAFF); 
        this.role = role;
        this.age = age;
    }

    @Override
    public UserRole getRole() {
        return UserRole.STAFF;
    }

    public StaffRole getStaffRole() {
        return role;
    }

    public void setStaffRole(StaffRole role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
