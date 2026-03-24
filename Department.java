import java.util.ArrayList;

public class Department {
    private String departmentName;
    private ArrayList<Course> courses;

    public Department(String department) {
        this.departmentName = department;
        courses = new ArrayList<>();
        courses.add(new Course(""));
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
