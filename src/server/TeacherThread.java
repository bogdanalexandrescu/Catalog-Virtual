package server;

import database.Database;
import entities.Student;
import entities.Subject;
import server.utilities.ImportNereusitException;
import server.utilities.XMLConvert;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by teo on 17.04.2017.
 */
public class TeacherThread implements Runnable {

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket socket;
	private Server server;
	private String name;
	private Database db;

	public TeacherThread(Socket socket, Server server, String name, Database db) {

		this.socket = socket;
		this.server = server;
		this.name = name;
		this.db = db;

	}

	@Override
	public void run() {
		try {
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			XMLConvert xml = new XMLConvert();

			Object message = "";

			// sendMessage("Congratulation! It works!");

			while (true) {

				// sendMessage("Say something except Finish");
				message = readMessage();
				if (message.equals("ExportStudents")) {
					String fileUrl = (String) readMessage();
					xml.convertStudentsToXML(fileUrl);
				}

				if (message.equals("ExportSituation")) {
					String nume = (String) readMessage();
					String fileUrl = (String) readMessage();
					xml.convertRaporStudentToXML(nume, fileUrl);
				}

				if (message.equals("ImportStudents")) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(0, "ImportStudents");
					String fileUrl = (String) readMessage();
					try {
						xml.insertStudentsFromXML(fileUrl);
						data.add("Import Reusit");
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						data.add("Import nereusit");
						e.printStackTrace();
					} catch (ImportNereusitException e) {
						data.add("Import nereusit");
						e.printStackTrace();
					}
					sendMessage(data);
				}

				if (message.equals("Login")) {

					String name = (String) readMessage();
					String password = (String) readMessage();
					String job = (String) readMessage();
					if (db.checkAccount(name, password, job) && job.equals("profesor")) {
						sendMessage("AcceptLoginTeacher");
						ArrayList<String> data = new ArrayList<String>();
						data.add("Teacher");
						data.add(name);
						data.add(db.selectMaterieByProfesor(name));
						data.add("" + db.selectEleviByMaterie(db.selectMaterieByProfesor(name)).size());
						sendMessage(data);
						if (db.selectEleviByMaterie(db.selectMaterieByProfesor(name)).size() != 0) {
							ArrayList<String> students = new ArrayList<String>();
							students = db.selectEleviByMaterie(db.selectMaterieByProfesor(name));
							students.add(0, "Students");
							sendMessage(students);
						}

					} else {
						if (db.checkAccount(name, password, job) && job.equals("admin")) {
							sendMessage("AcceptLoginHeadmaster");
							ArrayList<String> data = new ArrayList<String>();
							data.add("Headmaster");
							data.add(name);
							data.add("" + db.selectNumeProfesori().size());
							data.add("" + db.selectNumeElevi().size());
							data.add("" + db.selectNumeMaterii().size());
							sendMessage(data);

						} else {

							sendMessage("DeclineLogin");
						}
					}

				}

				if (message.equals("AddMark")) {

					String name = (String) readMessage();
					String subject = (String) readMessage();
					String teacher = (String) readMessage();
					int mark = (int) readMessage();
					String data = (String) readMessage();
					if (db.selectProfesorByMaterie(subject) != null)
						if (db.selectProfesorByMaterie(subject).equals(teacher)) {
							if (mark >= 1 && mark <= 10) {
								if (checkData(data)) {

									db.insertNotaElevWithDate(name, subject, mark, data);
									ArrayList<String> dataMark = new ArrayList<String>();
									dataMark.add("AddedMark");
									dataMark.add(name);
									sendMessage(dataMark);
								} else {
									sendMessage("Note: wrong date or invalid date format (dd/MM/yyyy)");
								}

							} else {

								sendMessage("unsuccessfully added mark");
							}
						}

				}

				if (message.equals("AddAbsence")) {
					ArrayList<String> dataAbsence = new ArrayList<String>();

					String name = (String) readMessage();
					String subject = (String) readMessage();
					String teacher = (String) readMessage();
					String data = (String) readMessage();

					if (db.selectProfesorByMaterie(subject) != null)
						if (db.selectProfesorByMaterie(subject).equals(teacher)) {
							if (checkData(data)) {
								db.insertAbsentaElevWithDate(name, subject, data);
								dataAbsence.add("AddedAbsence");
								dataAbsence.add(name);
								sendMessage(dataAbsence);
							} else {
								sendMessage("Note: wrong date or invalid date format (dd/MM/yyyy)");
							}
						}

				}
				if (message.equals("addSubjectAdmin")) {
					String headmasterName = (String) readMessage();
					String subject = (String) readMessage();
					db.insertMaterie(subject);

					ArrayList<String> data = new ArrayList<String>();
					data.add("Headmaster");
					data.add(headmasterName);
					data.add("" + db.selectNumeProfesori().size());
					data.add("" + db.selectNumeElevi().size());
					data.add("" + db.selectNumeMaterii().size());
					sendMessage(data);

					data = new ArrayList<String>();
					data = db.selectNumeMaterii();
					data.add(0, "SeeSubjects");
					sendMessage(data);
				}

				if (message.equals("Close App")) {
					sendMessage("Close App");
					break;
				}

				if (message.equals("addTeacherAdmin")) {
					String headmasterName = (String) readMessage();
					String name = (String) readMessage();
					String pass = (String) readMessage();
					String subject = (String) readMessage();

					ArrayList<String> checkMaterii = new ArrayList<String>();
					checkMaterii = db.selectNumeMaterii();
					if (checkMaterii.contains(subject)) {
						db.insertProfesor(name, pass, subject);

						ArrayList<String> data = new ArrayList<String>();
						data.add("Headmaster");
						data.add(headmasterName);
						data.add("" + db.selectNumeProfesori().size());
						data.add("" + db.selectNumeElevi().size());
						data.add("" + db.selectNumeMaterii().size());
						sendMessage(data);

						data = new ArrayList<String>();
						data = db.selectNumeProfesori();
						data.add(0, "SeeTeachers");
						int nr = data.size() - 1;
						for (int i = 0; i < nr; i++) {
							data.add(db.selectMaterieByProfesor(data.get(1 + i)));
						}
						sendMessage(data);
					}

				}

				if (message.equals("EditName")) {
					ArrayList<String> dataAbsence = new ArrayList<String>();

					String name = (String) readMessage();
					String subject = (String) readMessage();
					String newName = (String) readMessage();
					if (subject.equals("Teachers")) {
						db.updateNumeProfesor(name, newName);
						ArrayList<String> data = new ArrayList<String>();
						data = db.selectNumeProfesori();
						data.add(0, "SeeTeachers");
						int nr = data.size() - 1;
						for (int i = 0; i < nr; i++) {
							data.add(db.selectMaterieByProfesor(data.get(1 + i)));
						}
						sendMessage(data);
					}
					if (subject.equals("Subjects")) {
						db.updateNumeMaterie(name, newName);
						ArrayList<String> data = new ArrayList<String>();
						data = db.selectNumeMaterii();
						data.add(0, "SeeSubjects");
						sendMessage(data);
					}

				}

				if (message.equals("StudentSituation")) {
					ArrayList<Object> data = new ArrayList<Object>();
					String name = (String) readMessage();
					String subject = (String) readMessage();
					Subject subj = db.selectRaportElevByMaterie(name, subject);
					data.add(name);
					data.add(subj);
					sendMessage(data);

				}
				if (message.equals("SeeImportExport")) {

					sendMessage("SeeImportExport");

				}
				if (message.equals("StudentAllSituation")) {

					String name = (String) readMessage();
					Student student = db.selectRaportElev(name);
					sendMessage(student);

				}

				if (message.equals("Logout")) {
					sendMessage("Logout");
				}

				if (message.equals("Back")) {
					sendMessage("Back");
				}

				if (message.equals("SeeTeachers")) {
					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeProfesori();
					data.add(0, "SeeTeachers");
					int nr = data.size() - 1;
					for (int i = 0; i < nr; i++) {
						data.add(db.selectMaterieByProfesor(data.get(1 + i)));
					}
					sendMessage(data);

				}

				if (message.equals("SeeSubjects")) {
					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeMaterii();
					data.add(0, "SeeSubjects");

					sendMessage(data);

				}
				if (message.equals("SeeStudents")) {
					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeElevi();
					data.add(0, "SeeStudents");
					sendMessage(data);

				}
				if (message.equals("addStudentInterfeceAdmin")) {
					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeElevi();
					data.add(0, "addStudentInterface");
					sendMessage(data);
					data = db.selectNumeMaterii();
					data.add(0, "addSubjectInterface");
					sendMessage(data);
				}
				if (message.equals("editStudentInterfeceAdmin")) {
					String nume = (String) readMessage();
					ArrayList<String> data = new ArrayList<String>();
					data.add("SendStudentName");
					data.add(nume);
					sendMessage(data);

					data = new ArrayList<String>();

					data = db.selectMateriiByElev(nume);
					data.add(0, "editStudentInterface");
					sendMessage(data);

					data = db.selectNumeElevi();
					data.add(0, "editStudentsInterface");
					sendMessage(data);

				}
				if (message.equals("SituationOfStudents")) {

					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeElevi();
					data.add(0, "SituationOfStudents");
					sendMessage(data);
				}
				if (message.equals("AddStudentSubject")) {

					String name = (String) readMessage();
					String subject = (String) readMessage();
					db.insertElevMaterie(name, subject);

					ArrayList<String> data = new ArrayList<String>();
					data.add("SendStudentName");
					data.add(name);
					sendMessage(data);

					data = new ArrayList<String>();

					data = db.selectMateriiByElev(name);
					data.add(0, "editStudentInterface");
					sendMessage(data);

					data = db.selectNumeElevi();
					data.add(0, "editStudentsInterface");
					sendMessage(data);
				}
				if (message.equals("DeleteStudentMark")) {

					String name = (String) readMessage();
					String subject = (String) readMessage();
					String date = (String) readMessage();
					if (checkData(date)) {
						db.deleteNotaElev(name, subject, date);
						ArrayList<String> data = new ArrayList<String>();
						data.add("SendStudentName");
						data.add(name);
						sendMessage(data);

						data = new ArrayList<String>();

						data = db.selectMateriiByElev(name);
						data.add(0, "editStudentInterface");
						sendMessage(data);

						data = db.selectNumeElevi();
						data.add(0, "editStudentsInterface");
						sendMessage(data);
					}

				}
				if (message.equals("DeleteStudentAbsence")) {

					String name = (String) readMessage();
					String subject = (String) readMessage();
					String date = (String) readMessage();
					if (checkData(date)) {
						db.deleteAbsentaElev(name, subject, date);
						ArrayList<String> data = new ArrayList<String>();
						data.add("SendStudentName");
						data.add(name);
						sendMessage(data);

						data = new ArrayList<String>();

						data = db.selectMateriiByElev(name);
						data.add(0, "editStudentInterface");
						sendMessage(data);

						data = db.selectNumeElevi();
						data.add(0, "editStudentsInterface");
						sendMessage(data);
					}

				}
				if (message.equals("FinishEditStudent")) {

					String oldName = (String) readMessage();
					String newName = (String) readMessage();
					db.updateNumeElev(oldName, newName);
					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeElevi();
					data.add(0, "SeeStudents");
					sendMessage(data);

				}

				if (message.equals("DeleteStudentSubject")) {

					String name = (String) readMessage();
					String subject = (String) readMessage();
					db.deleteMaterieElev(name, subject);

					ArrayList<String> data = new ArrayList<String>();
					data.add("SendStudentName");
					data.add(name);
					sendMessage(data);

					data = new ArrayList<String>();

					data = db.selectMateriiByElev(name);
					data.add(0, "editStudentInterface");
					sendMessage(data);

					data = db.selectNumeElevi();
					data.add(0, "editStudentsInterface");
					sendMessage(data);
				}
				if (message.equals("Add Student")) {

					String name = (String) readMessage();
					db.insertElevi(name);
					ArrayList<String> data = (ArrayList<String>) readMessage();
					for (int i = 0; i < data.size(); i++) {
						db.insertElevMaterie(name, data.get(i));
					}

					data = db.selectNumeElevi();
					data.add(0, "addStudentInterface");
					sendMessage(data);
					data = db.selectNumeMaterii();
					data.add(0, "addSubjectInterface");
					sendMessage(data);
				}
				if (message.equals("Add Student Simple")) {

					String name = (String) readMessage();
					db.insertElevi(name);
					ArrayList<String> data = db.selectNumeElevi();
					data.add(0, "addStudentInterface");
					sendMessage(data);
					data = db.selectNumeMaterii();
					data.add(0, "addSubjectInterface");
					sendMessage(data);
				}
				if (message.equals("Delete")) {
					String headmasterName = (String) readMessage();
					String name = (String) readMessage();
					String subject = (String) readMessage();
					if (subject.equals("Subjects")) {
						db.deleteMaterie(name);
						ArrayList<String> data = new ArrayList<String>();
						data.add("Headmaster");
						data.add(headmasterName);
						data.add("" + db.selectNumeProfesori().size());
						data.add("" + db.selectNumeElevi().size());
						data.add("" + db.selectNumeMaterii().size());
						sendMessage(data);

						data = new ArrayList<String>();
						data = db.selectNumeMaterii();
						data.add(0, "SeeSubjects");
						sendMessage(data);

					}
					if (subject.equals("Students")) {
						db.deleteElev(name);
						ArrayList<String> data = new ArrayList<String>();
						data.add("Headmaster");
						data.add(headmasterName);
						data.add("" + db.selectNumeProfesori().size());
						data.add("" + db.selectNumeElevi().size());
						data.add("" + db.selectNumeMaterii().size());
						sendMessage(data);

						data = new ArrayList<String>();
						data = db.selectNumeElevi();
						data.add(0, "SeeStudents");
						sendMessage(data);

					}
					if (subject.equals("Teachers")) {

						db.deleteProfesor(name);
						ArrayList<String> data = new ArrayList<String>();
						data.add("Headmaster");
						data.add(headmasterName);
						data.add("" + db.selectNumeProfesori().size());
						data.add("" + db.selectNumeElevi().size());
						data.add("" + db.selectNumeMaterii().size());
						sendMessage(data);

						data = new ArrayList<String>();
						data = db.selectNumeProfesori();
						data.add(0, "SeeTeachers");
						int nr = data.size() - 1;
						for (int i = 0; i < nr; i++) {
							data.add(db.selectMaterieByProfesor(data.get(1 + i)));
						}
						sendMessage(data);
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	public Object readMessage() throws IOException, ClassNotFoundException {
		return input.readObject();
	}

	public void sendMessage(Object message) {
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static boolean checkData(String data) {
		if (data.length() != 10)
			return false;
		final String DATE_FORMAT = "dd/MM/yyyy";
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(data);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
			LocalDate date = LocalDate.parse(data, formatter);

			LocalDate localDate = LocalDate.now();
			if (!date.isAfter(localDate)) {
				return true;
			}

			return false;
		} catch (ParseException e) {
			return false;
		}
	}

	public void close() {
		try {
			input.close();
			output.close();
			socket.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}