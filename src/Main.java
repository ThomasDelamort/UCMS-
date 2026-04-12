import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static ArrayList<Department> departments = new ArrayList<>();
    static ArrayList<Student> registeredStudents = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Module> modules = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int choice;

    public static void main(String[] args) {
        do {
            if (courses.isEmpty()) {
                menuAdd();
            } else {
                menu();
            }

            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> addDepartment();
                case 3 -> addCourse();
                case 4 -> assignCourseToDepartment();
                case 5 -> addModule();
                case 6 -> assignModuleToCourse();
                case 7 -> enrollStudentToCourse();
                case 8 -> displayAll();
                case 9 -> displayStudents();
                case 10 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 10);
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

    static void addCourse() {
        if (departments.isEmpty()) {
            System.out.print("Enter Course Name: ");
            String courseName = sc.nextLine();
            Course course = new Course(courseName, null);
            courses.add(course);
            System.out.println("Course added!\n");
        } else {
            System.out.print("Enter Course Name: ");
            String courseName = sc.nextLine();
            Course course = new Course(courseName, null);
            courses.add(course);
            System.out.println("Would you like to assign Course to department?");
            System.out.println("1. Yes\n2. No\n");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    displayDepartments();
                    System.out.print("Select Department: ");
                    int index = sc.nextInt(); sc.nextLine();
                    index -= 1;

                    if (index >= 0 && index < departments.size()) {
                        departments.get(index).addCourse(course);
                        System.out.println("Course assigned!\n");
                    } else {
                        System.out.println("Invalid index\n");
                    }
                }
                case 2 -> {
                    System.out.println("Course added!\n");
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    static void assignCourseToDepartment() {
        if (departments.isEmpty()) {
            System.out.println("No Departments\n");
        } else {
            courseListDisplay();
            System.out.print("Enter Course Index: ");
            int cIndex = sc.nextInt(); sc.nextLine();
            cIndex -= 1;

            displayDepartments();
            System.out.print("Select Department: ");
            int dIndex = sc.nextInt(); sc.nextLine();
            dIndex -= 1;

            if (cIndex >= 0 && dIndex >= 0) {
                departments.get(dIndex).addCourse(courses.get(cIndex));
                System.out.println("Course assigned!\n");
            } else {
                System.out.println("Invalid index\n");
            }
        }
    }


    static void addModule() {
        if (courses.isEmpty()) {
            System.out.println("No Courses\n");
        } else {
            System.out.print("Enter Module Name: ");
            String moduleName = sc.nextLine();
            Module newModule = new Module(moduleName);
            modules.add(newModule);
            if (courses.isEmpty()) {
                System.out.println("No courses available. Module stored only.\n");
                return;
            }
            System.out.println("Would you like to assign Module to a Course?");
            System.out.println("1. Yes\n2. No");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    courseListDisplay();
                    System.out.print("Select Course: ");
                    int clIndex = sc.nextInt(); sc.nextLine();
                    clIndex -= 1;

                    if (clIndex >= 0 && clIndex < courses.size()) {
                        courses.get(clIndex).addModule(newModule);
                        System.out.println("Module added and assigned!\n");
                    } else {
                        System.out.println("Invalid course index\n");
                    }
                }
                case 2 -> {
                    System.out.println("Module added (not assigned).\n");
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    static void assignModuleToCourse() {
        if (courses.isEmpty()) {
            System.out.println("No Courses\n");
        } else {
            modulesListDisplay();
            System.out.println("Enter Module Index: ");
            int mIndex = sc.nextInt(); sc.nextLine();
            mIndex -= 1;

            courseListDisplay();
            System.out.println("Enter Course Index: ");
            int clIndex = sc.nextInt(); sc.nextLine();
            clIndex -= 1;

            if (clIndex >= 0 && clIndex < courses.size()
                    && mIndex >= 0 && mIndex < modules.size()) {
                courses.get(clIndex).addModule(modules.get(mIndex));
                System.out.println("Module assigned!\n");
            } else {
                System.out.println("Invalid index\n");
            }
        }
    }

    static void enrollStudentToCourse() {
        if (registeredStudents.isEmpty()) {
            System.out.println("No students\n");
        } else if (courses.isEmpty()) {
            System.out.println("No Courses\n");
        } else {
            displayStudents();
            System.out.print("Select Student: ");
            int sIndex = sc.nextInt(); sc.nextLine();
            sIndex -= 1;

            if (sIndex < 0 || sIndex >= registeredStudents.size()) {
                System.out.println("Invalid student\n");
                return;
            }

            courseListDisplay();
            System.out.print("Select Course: ");
            int cIndex = sc.nextInt(); sc.nextLine();
            cIndex -= 1;

            if (cIndex < 0 || cIndex >= courses.size()) {
                System.out.println("Invalid course\n");
            } else {
                courses.get(cIndex).enrollStudent(registeredStudents.get(sIndex));
                System.out.println("Student enrolled!\n");
            }
        }
    }

    static void displayAll() {
        if (departments.isEmpty()) {
            System.out.println("No data\n");
            return;
        }
        for (Department d : departments) {
            System.out.println("\nDepartment: " + d.getDepartmentName());

            if (d.getCourses().isEmpty()) {
                System.out.println(" No courses");
            } else {
                for (Course c : d.getCourses()) {
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
        for (int i = 0; i < registeredStudents.size(); i++) {
            System.out.println(i+1 + ". " + registeredStudents.get(i).getStudentName());
        }
        System.out.println();
    }

    static void displayDepartments() {
        System.out.println("\n===== Departments =====");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println(i+1 + ". " + departments.get(i).getDepartmentName());
        }
        System.out.println();
    }

    static void displayCourses(Department dept) {
        System.out.println("\n===== Courses =====");
        for (int i = 0; i < dept.getCourses().size(); i++) {
            System.out.println(i+1 + ". " + dept.getCourses().get(i).getCourseName());
        }
        System.out.println();
    }

    static void courseListDisplay() {
        System.out.println("\n===== Courses =====");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i+1 + ". " + courses.get(i).getCourseName());
        }
    }

    static void modulesListDisplay() {
        System.out.println("\n===== Modules =====");
        for (int i = 0; i < modules.size(); i++) {
            System.out.println(i+1 + ". " + modules.get(i).getModuleName());
        }
    }

    static void menu() {
        System.out.println("""
                    ==== University Management System ====
                    1. Register Student
                    2. Add Department
                    3. Add Course
                    4. Assign Course to Department
                    5. Add Module
                    6. Assign Module to Course
                    7. Enroll Student to Course
                    8. Display All Information
                    9. Display Students
                    10. Exit
                    """);
    }

    static void menuAdd() {
        System.out.println("""
                    ==== University Management System ====
                    1. Register Student
                    2. Add Department
                    3. Add Course
                    4. Assign Course to Department
                    5. Add Module (Unavailable)
                    6. Assign Module to Course (Unavailable)
                    7. Enroll Student to Course
                    8. Display All Information
                    9. Display Students
                    10. Exit
                    """);
    }
}