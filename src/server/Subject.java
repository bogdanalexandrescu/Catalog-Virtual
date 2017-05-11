package server;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by teo on 16.04.2017.
 */
public class Subject {

	String name;
	String nameTeacher;
	ArrayList<Mark> marks = new ArrayList<Mark>();
	ArrayList<Absence> absences = new ArrayList<Absence>();

	public Subject(String name, String nameTeacher, ArrayList<Mark> marks, ArrayList<Absence> absences) {
		super();
		this.name = name;
		this.marks = marks;
		this.absences = absences;
		this.nameTeacher = nameTeacher;
	}

	public Subject() {

	}

	public String getNameTeacher() {
		return nameTeacher;
	}

	public void setNameTeacher(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Mark> getMarks() {
		return marks;
	}

	@XmlElement(name = "mark")
	public void setMarks(ArrayList<Mark> marks) {
		this.marks = marks;
	}

	public ArrayList<Absence> getAbsences() {
		return absences;
	}

	@XmlElement(name = "absence")
	public void setAbsences(ArrayList<Absence> absences) {
		this.absences = absences;
	}

}
