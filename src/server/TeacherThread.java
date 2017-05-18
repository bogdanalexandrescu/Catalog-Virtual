package server;

import database.Database;

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

			Object message = "";

			// sendMessage("Congratulation! It works!");

			while (true) {

				// sendMessage("Say something except Finish");
				message = readMessage();

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
						System.out.println(name + " " + password + " " + job);
						System.out.println(db.checkAccount(name, password, job));
						System.out.print(job.equals("admin"));
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
							System.out.println("DeclineLogin");
							sendMessage("DeclineLogin");
						}
					}

				}

				if (message.equals("AddMark")) {

					String name = (String) readMessage();
					String subject = (String) readMessage();
					int mark = (int) readMessage();
					String data = (String) readMessage();
					System.out.println(data + " 07/07/2017");
					if (mark >= 1 && mark <= 10) {
						if (checkData(data)) {
							System.out.print(name + " " + subject + " " + mark);
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

				if (message.equals("AddAbsence")) {
					ArrayList<String> dataAbsence = new ArrayList<String>();

					String name = (String) readMessage();
					String subject = (String) readMessage();
					String data = (String) readMessage();
					System.out.println(data + " " + data.length());
					if (checkData(data)) {
						db.insertAbsentaElevWithDate(name, subject, data);
						dataAbsence.add("AddedAbsence");
						dataAbsence.add(name);
						sendMessage(dataAbsence);
					} else {
						sendMessage("Note: wrong date or invalid date format (dd/MM/yyyy)");
					}

				}
				if (message.equals("addSubjectAdmin")) {
					String subject = (String) readMessage();
					db.insertMaterie(subject);

					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeMaterii();
					data.add(0, "SeeSubjects");
					System.out.println(data.size());
					sendMessage(data);
				}

				if (message.equals("addTeacherAdmin")) {
					String name = (String) readMessage();
					String pass = (String) readMessage();
					String subject = (String) readMessage();
					db.insertProfesor(name, pass, subject);

					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeProfesori();
					data.add(0, "SeeTeachers");
					int nr = data.size() - 1;
					for (int i = 0; i < nr; i++) {
						data.add(db.selectMaterieByProfesor(data.get(1 + i)));
					}
					sendMessage(data);
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
					System.out.println(data.size());
					sendMessage(data);

				}
				if (message.equals("SeeStudents")) {
					ArrayList<String> data = new ArrayList<String>();
					data = db.selectNumeElevi();
					data.add(0, "SeeStudents");
					sendMessage(data);

				}
				if (message.equals("Delete")) {
					String name = (String) readMessage();
					String subject = (String) readMessage();
					if (subject.equals("Subjects")) {
						db.deleteMaterie(name);
						ArrayList<String> data = new ArrayList<String>();
						data = db.selectNumeMaterii();
						data.add(0, "SeeSubjects");
						sendMessage(data);

					}
					if (subject.equals("Students")) {
						db.deleteElev(name);
						ArrayList<String> data = new ArrayList<String>();
						data = db.selectNumeElevi();
						data.add(0, "SeeStudents");
						sendMessage(data);

					}
					if (subject.equals("Teachers")) {

						db.deleteProfesor(name);
						ArrayList<String> data = new ArrayList<String>();
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
