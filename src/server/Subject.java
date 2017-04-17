package server;

import java.util.ArrayList;

/**
 * Created by teo on 16.04.2017.
 */
public class Subject {

    String name;
    ArrayList<Student> students;
    Teacher teacher;

    public Subject(String name, ArrayList<Student> students, Teacher teacher) {
        this.name = name;
        this.students = students;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents (ArrayList<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


}
