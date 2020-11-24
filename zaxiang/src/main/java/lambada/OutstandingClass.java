package lambada;

import java.util.List;

public class OutstandingClass {
    String name;
    List<Student> students;

    public OutstandingClass(String name, List<Student> students) {

        this.name = name;
        this.students = students;
    }

    public OutstandingClass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
