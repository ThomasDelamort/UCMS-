public class Student {
    private String studentID;
    private String studentName;

    public Student(String id, String name) {
        this.studentID = id;
        this.studentName = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }
}
