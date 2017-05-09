package server;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="students")
public class StudentsWrapper {
	ArrayList<Student> students;

	public ArrayList<Student> getStudents() {
		return students;
	}
	@XmlElement(name="student")
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	
}
