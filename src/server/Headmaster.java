package server;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import database.Database;

/**
 * Created by teo on 16.04.2017.
 */
@XmlRootElement
public class Headmaster {

	String name;
	ArrayList<Student> students;
	Database db;

	public Headmaster(String name, ArrayList<Student> students, Database db) {
		super();
		this.name = name;
		this.students = students;
		this.db = db;
	}

	public Headmaster(){
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

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public Database getDb() {
		return db;
	}

	public void setDb(Database db) {
		this.db = db;
	}

}
