package server;

import java.util.ArrayList;

/**
 * Created by teo on 16.04.2017.
 */
public class Student  {

    String name;
    ArrayList<Mark> marks;
    ArrayList<Absence> absences;


    public Student(String name, ArrayList<Mark> marks, ArrayList<Absence> absences) {
        this.name = name;
        this.marks = marks;
        this.absences = absences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Mark> marks) {
        this.marks = marks;
    }

    public ArrayList<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(ArrayList<Absence> absences) {
        this.absences = absences;
    }
}
