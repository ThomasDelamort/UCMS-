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
