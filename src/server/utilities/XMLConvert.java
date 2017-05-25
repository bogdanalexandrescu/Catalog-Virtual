package server.utilities;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import database.Database;
import entities.Absence;
import entities.Mark;
import entities.Student;
import entities.StudentsWrapper;
import entities.Subject;

public class XMLConvert {
	public void convertRaporStudentToXML(String nume, String path) {
		Database db = new Database();
		try {
			Student student = db.selectRaportElev(nume);
			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(student, file);
			jaxbMarshaller.marshal(student, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void insertStudentsFromXML(String fileURL) throws JAXBException, ImportNereusitException {
		StudentsWrapper sw = null;
		Database db = new Database();

		File file = new File(fileURL);
		JAXBContext jaxbContext = JAXBContext.newInstance(StudentsWrapper.class, Student.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		if (jaxbUnmarshaller.unmarshal(file) instanceof StudentsWrapper) {
			sw = (StudentsWrapper) jaxbUnmarshaller.unmarshal(file);
			for (Student s : sw.getStudents()) {
				try {
					db.insertElevi(s.getName());

					for (Subject sb : s.getSubjects()) {
						if (!db.selectNumeMaterii().contains(sb.getName()))
							db.insertMaterie(sb.getName());
						db.insertElevMaterie(s.getName(), sb.getName());
						for (Mark m : sb.getMarks()) {
							db.insertNotaElevWithDate(s.getName(), sb.getName(), m.getMark(), m.getData());
						}
						for (Absence a : sb.getAbsences()) {
							db.insertAbsentaElevWithDate(s.getName(), sb.getName(), a.getData());
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else
			throw new ImportNereusitException();

	}

	public void convertStudentsToXML(String fileUrl) {
		StudentsWrapper sw = new StudentsWrapper();
		Database db = new Database();

		try {
			ArrayList<Student> students = new ArrayList<Student>();
			ArrayList<String> numeElevi = db.selectNumeElevi();
			numeElevi = db.selectNumeElevi();
			for (String nume : numeElevi) {
				students.add(db.selectRaportElev(nume));
			}
			sw.setStudents(students);
			File file = new File(fileUrl);
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentsWrapper.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(sw, file);
			jaxbMarshaller.marshal(sw, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

}
