public class TwoWayAssociation {
    public static void main(String[] args) {
        System.out.println();
    }
}

class Teacher {
    private String name;
    private String employeeID;
    private String designation;
    private Student[] students;
 
    public Employee(String name, String employeeID, String designation) {
        this.name = name;
        this.employeeID = employeeID;
        this.designation = designation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Student[] getStudents() {
        return this.students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}

class Student {
    private String name;
    private String studentID;
    private String grade;
    private Teacher[] teachers;
 
    public student(String name, String studentID, String grade) {
        this.name = name;
        this.studentID = studentID;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Teacher[] getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Teacher[] teachers) {
        this.teachers = teachers;
    }
}