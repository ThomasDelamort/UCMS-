import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<Module> modules;
    private ArrayList<Student> enrolledStudents;

    public Course(String name) {
        this.courseName = name;
        modules = new ArrayList<>();
        enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void displayCourse() {
        System.out.println("   Course: " + courseName);

        System.out.println("    Modules:");
        if (modules.isEmpty()) {
            System.out.println("     None");
        } else {
            for (int i = 0; i < modules.size(); i++) {
                System.out.println("     - " + modules.get(i).getModuleName());
            }
        }

        System.out.println("    Students:");
        if (enrolledStudents.isEmpty()) {
            System.out.println("     None");
        } else {
            for (Student s : enrolledStudents) {
                System.out.println("     - " + s.getStudentName());
            }
        }
    }
