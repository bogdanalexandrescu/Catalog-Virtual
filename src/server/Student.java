package server;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by teo on 16.04.2017.
 */
@XmlRootElement
public class Student implements Serializable {

    String name;
    ArrayList<Subject> subjects = new ArrayList<Subject>();
    
    
	public Student(String name, ArrayList<Subject> subjects) {
		super();
		this.name = name;
		this.subjects = subjects;
	}
	public Student(){
	}

	
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Subject> getSubjects() {
		return subjects;
	}
	@XmlElement(name="subject")
	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}
   
    
}
