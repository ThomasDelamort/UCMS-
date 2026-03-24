import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static ArrayList<Department> departments = new ArrayList<>();
    static ArrayList<Student> registeredStudents = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int choice;

    public static void main(String[] args) {
        departments.add(new  Department(""));
        registeredStudents.add(new Student("", ""));

        do {
            System.out.println("""
                    ==== University Management System ====
                    1. Register Student
                    2. Add Department
                    3. Assign Course to Department
                    4. Add Module to Course
                    5. Enroll Student to Course
                    6. Display All Information
                    7. Display Students
                    8. Exit
                    """);

            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> addDepartment();
                case 3 -> assignCourseToDepartment();
                case 4 -> addModuleToCourse();
                case 5 -> enrollStudentToCourse();
                case 6 -> displayAll();
                case 7 -> displayStudents();
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 8);
    }

    static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        registeredStudents.add(new Student(id, name));
        System.out.println("Student registered!\n");
    }

    static void addDepartment() {
        System.out.print("Enter Department Name: ");
        String name = sc.nextLine();

        departments.add(new Department(name));
        System.out.println("Department added!\n");
    }

    static void assignCourseToDepartment() {
        if (departments.isEmpty()) {
            System.out.println("No departments available\n");
            return;
        } else {
            System.out.print("Enter Course Name: ");
            String courseName = sc.nextLine();

            displayDepartments();
            System.out.print("Select Department Index: ");
            int index = sc.nextInt(); sc.nextLine();

            if (index >= 0 && index < departments.size()) {
                departments.get(index).addCourse(new Course(courseName));
                System.out.println("Course added!\n");
            } else {
                System.out.println("Invalid index\n");
            }
        }
    }

    static void addModuleToCourse() {
        if (departments.isEmpty()) {
            System.out.println("No departments\n");
            return;
        }

        displayDepartments();
        System.out.print("Select Department: ");
        int dIndex = sc.nextInt(); sc.nextLine();

        if (dIndex < 0 || dIndex >= departments.size()) {
            System.out.println("Invalid department\n");
            return;
        }

        Department dept = departments.get(dIndex);

        if (dept.getCourses().isEmpty()) {
            System.out.println("No courses\n");
            return;
        }

        displayCourses(dept);
        System.out.print("Select Course: ");
        int cIndex = sc.nextInt(); sc.nextLine();

        if (cIndex < 0 || cIndex >= dept.getCourses().size()) {
            System.out.println("Invalid course\n");
            return;
        }

        System.out.print("Enter Module Name: ");
        String moduleName = sc.nextLine();

        dept.getCourses().get(cIndex).addModule(new Module(moduleName));
        System.out.println("Module added!\n");
    }

    static void enrollStudentToCourse() {
        if (registeredStudents.isEmpty()) {
            System.out.println("No students\n");
            return;
        }

        if (departments.isEmpty()) {
            System.out.println("No departments\n");
            return;
        }

        displayStudents();
        System.out.print("Select Student: ");
        int sIndex = sc.nextInt(); sc.nextLine();

        if (sIndex < 0 || sIndex >= registeredStudents.size()) {
            System.out.println("Invalid student\n");
            return;
        }

        displayDepartments();
        System.out.print("Select Department: ");
        int dIndex = sc.nextInt(); sc.nextLine();

        if (dIndex < 0 || dIndex >= departments.size()) {
            System.out.println("Invalid department\n");
            return;
        }

        Department dept = departments.get(dIndex);

        if (dept.getCourses().isEmpty()) {
            System.out.println("No courses\n");
            return;
        }

        displayCourses(dept);
        System.out.print("Select Course: ");
        int cIndex = sc.nextInt(); sc.nextLine();

        if (cIndex < 0 || cIndex >= dept.getCourses().size()) {
            System.out.println("Invalid course\n");
            return;
        }

        dept.getCourses().get(cIndex).enrollStudent(registeredStudents.get(sIndex));
        System.out.println("Student enrolled!\n");
    }

    static void displayAll() {
        if (departments.isEmpty()) {
            System.out.println("No data\n");
            return;
        }
        int i = 1;
        for (Department d : departments) {
            if (i == 1) {
                i++;
                continue;
            }
            System.out.println("\nDepartment: " + d.getDepartmentName());

            if (d.getCourses().isEmpty()) {
                System.out.println(" No courses");
            } else {
                int j = 1;
                for (Course c : d.getCourses()) {
                    if (j == 1) {
                        j++;
                        continue;
                    }
                    c.displayCourse();
                }
            }

            System.out.println();
        }
    }

    static void displayStudents() {
        System.out.println("\n===== Students =====");
        if (registeredStudents.isEmpty()) {
            System.out.println("No students\n");
            return;
        }
        for (int i = 1; i < registeredStudents.size(); i++) {
            System.out.println(i + ". " + registeredStudents.get(i).getStudentName());
        }
        System.out.println();
    }

    static void displayDepartments() {
        System.out.println("\n===== Departments =====");
        for (int i = 1; i < departments.size(); i++) {
            System.out.println(i + ". " + departments.get(i).getDepartmentName());
        }
        System.out.println();
    }

    static void displayCourses(Department dept) {
        System.out.println("\n===== Courses =====");
        for (int i = 1; i < dept.getCourses().size(); i++) {
            System.out.println(i + ". " + dept.getCourses().get(i).getCourseName());
        }
        System.out.println();
    }
}