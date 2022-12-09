public class Inheritance {
    public static void main(String[] args) {
        System.out.println("test?");
    }
}

class Person {
    private String ssn;
    private String name;

    public Person(String ssn, String name) {
        this.ssn = ssn;
        this.name = name;
    }

    public String getSSN() {
        return this.SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Person{ssn:" + this.getSSN() + ", name: " + this.getName() + "}\n";
    } 
}

class Employee extends Person {
    private String employeeID;
    private String designation;

    public Employee(String ssn, String name, String employeeID, String designation) {
        super(ssn, name);
        this.employeeID = employeeID;
        this.designation = designation;
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

    @Override
    public String toString(){
        return "Person{ssn:" + this.getSSN() + ", name: " + this.getName() + ", employeeID: " + this.getEmployeeID() + ", designation: " + this.getDesignation() + "}\n";
    } 
}