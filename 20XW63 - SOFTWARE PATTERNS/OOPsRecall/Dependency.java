public class Dependency {
    public static void main(String[] args) {
        System.out.println();
    }
}

class Course {
    private String name;
    private HashMap<Student, Integer> marks;

    public Course(String name, HashMap<Student, Integer> marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Student, Integer> getMarks() {
        return this.marks;
    }

    public void setMarks(HashMap<Student, Integer> marks) {
        this.marks=marks;
    }
}

class CourseStats {
    public calcAverage(Course course) {
        Integer sumOfMarks = 0;
        Integer count = 0;
        for (Map.Entry<Student, Integer> entry: course.marks.entrySet()){
            count++;
            sumOfMarks+= entry.getValue();
        }
        System.out.println("AVERAGE MARKS: " + sumOfMarks + ".");
    }
}