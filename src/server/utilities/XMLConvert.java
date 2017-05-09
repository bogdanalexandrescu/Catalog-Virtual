package server.utilities;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import database.Database;
import server.Student;
import server.StudentsWrapper;
import server.Subject;

public class XMLConvert {
	public void convertRaporStudentToXML(Student student) {

		try {

			File file = new File("C:\\Users\\Bogdan\\Desktop\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(student, file);
			jaxbMarshaller.marshal(student, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public StudentsWrapper insertStudentsFromXML(String fileURL) {
		StudentsWrapper sw = null;
		try {

			File file = new File(fileURL);
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentsWrapper.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			sw = (StudentsWrapper) jaxbUnmarshaller.unmarshal(file);
			System.out.println(sw);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw;
	}
	
	public void convertStudentsToXML() throws SQLException {
		StudentsWrapper sw = new StudentsWrapper();
		Database db = new Database();
		
		try {
			ArrayList<Student> students = new ArrayList<Student>();
			ArrayList<String> numeElevi = db.selectNumeElevi();
			for(String elev: numeElevi){
				ArrayList<String> numeMaterii = db.selectMateriiByElev(elev);
				ArrayList<Subject> subjects = new ArrayList<Subject>();
				for(String materie: numeMaterii)
					subjects.add(new Subject(materie,db.selectProfesorByMaterie(materie),null,null));
				students.add(new Student(elev,subjects));
			}
			sw.setStudents(students);
			File file = new File("C:\\Users\\Bogdan\\Desktop\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentsWrapper.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(sw, file);
			jaxbMarshaller.marshal(sw, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
