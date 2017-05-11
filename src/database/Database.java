package database;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;

import server.Absence;
import server.Mark;
import server.Student;
import server.StudentsWrapper;
import server.Subject;
import server.utilities.XMLConvert;

public class Database {
	// JDBC driver name and database URL
	static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

	// Database credentials
	static final String DB_USER = "SYSTEM";
	static final String DB_PASSWORD = "alexbogdan";

	public void insertElevi(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			// if(conn.equals(null))
			// System.out.println("teo");
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO ELEVi" + "(ID,NUME) VALUES" + "(seq_elev.nextval, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public void insertMaterie(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO Materie" + " VALUES " + "(seq_materie.nextval, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public void insertProfesor(String nume, String parola, String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO ACCOUNTS (ID,NUME,PAROLA,ROL,ID_MATERIE)" + "VALUES"
					+ "(seq_accounts.nextval,?,?,'profesor',(select id from materie where nume = ? ))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, parola);
			pstmt.setString(3, materie);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public void insertElevMaterie(String numeElev, String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO ELEV_MATERIE (ID,ID_ELEV,ID_MATERIE)" + "VALUES"
					+ "(seq_elev_materie.nextval,(select id from elevi where nume=?),(select id from materie where nume = ? ))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, numeElev);
			pstmt.setString(2, materie);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public void insertNotaElev(String nume, String materie, int nota) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO Note (ID,ID_ELEV,ID_MATERIE,NOTA,DATA)" + "VALUES"
					+ "(seq_note.nextval,(select id from elevi where nume=?),(select id from materie where nume = ? ),?,TO_DATE(sysdate,'YY-MM-DD'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			pstmt.setInt(3, nota);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public void insertNotaElevWithDate(String nume, String materie, int nota, String data) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO Note (ID,ID_ELEV,ID_MATERIE,NOTA,DATA)" + "VALUES"
					+ "(seq_note.nextval,(select id from elevi where nume=?),(select id from materie where nume = ? ),?,TO_DATE(?,'DD-MM-YY'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			pstmt.setInt(3, nota);
			pstmt.setString(4, data.substring(2, 10));
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public void insertAbsentaElev(String nume, String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO ABSENTE (ID,ID_ELEV,ID_MATERIE,DATA)" + "VALUES"
					+ "(seq_absente.nextval,(select id from elevi where nume=?),(select id from materie where nume = ? ),TO_DATE(sysdate,'YY-MM-DD'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public void insertAbsentaElevWithDate(String nume, String materie,String data) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO ABSENTE (ID,ID_ELEV,ID_MATERIE,DATA)" + "VALUES"
					+ "(seq_absente.nextval,(select id from elevi where nume=?),(select id from materie where nume = ? ),TO_DATE(?,'DD-MM-YY'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			pstmt.setString(4, data.substring(2, 10));
			pstmt.executeUpdate();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try
		System.out.println("Goodbye!");
	}

	public boolean checkAccount(String nume, String parola, String rol) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			String sql = "select id from accounts where nume=? and parola=? and rol=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, parola);
			pstmt.setString(3, rol);
			ResultSet rs = pstmt.executeQuery();

			return rs.next();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return false;
	}

	public String selectMaterieByProfesor(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			String sql = "select nume from materie where id=(select id_materie from accounts where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("NUME");
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return "";
	}

	public ArrayList<String> selectNumeElevi() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> numeElevi = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			String sql = "select nume from elevi";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				numeElevi.add(rs.getString(1));
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return numeElevi;
	}

	public ArrayList<String> selectNumeProfesori() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> numeProfesori = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			String sql = "select nume from accounts where rol='profesor'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				numeProfesori.add(rs.getString(1));
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return numeProfesori;
	}

	public String selectProfesorByMaterie(String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String profesor = null;
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");

			String sql = "select nume from accounts where id_materie = (select id from materie where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, materie);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				profesor = rs.getString(1);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return profesor;
	}

	public ArrayList<String> selectNumeMaterii() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> numeMaterii = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");
			String sql = "select nume from materie";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				numeMaterii.add(rs.getString(1));
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return numeMaterii;
	}

	public ArrayList<String> selectMateriiByElev(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ArrayList<String> numeMaterii = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");
			String sql = "select id_materie from elev_materie where id_elev = (select id from elevi where nume=?)";
			String sql2 = "select nume from materie where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setString(1, nume);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pstmt2.setLong(1, rs.getInt(1));
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				numeMaterii.add(rs2.getString(1));
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (pstmt2 != null)
				pstmt2.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return numeMaterii;
	}

	public ArrayList<String> selectEleviByMaterie(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ArrayList<String> numeElevi = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");
			String sql = "select id_elev from elev_materie where id_materie = (select id from materie where nume=?)";
			String sql2 = "select nume from elevi where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setString(1, nume);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pstmt2.setLong(1, rs.getInt(1));
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
				numeElevi.add(rs2.getString(1));
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (pstmt2 != null)
				pstmt2.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return numeElevi;
	}

	public Subject selectRaportElevByMaterie(String elev, String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<Absence> absences = new ArrayList<Absence>();
		ArrayList<Mark> marks = new ArrayList<Mark>();
		Subject subject = null;
		String profesor = null;
		try {
			// STEP 2: Register JDBC driver
			Database db = new Database();
			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = getDBConnection();
			System.out.println("Connected database successfully...");
			String sql = "select nota,to_date(data,'YY-MM-DD') from note where id_materie = (select id from materie where nume=?) and id_elev = (select id from elevi where nume=?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, materie);
			pstmt.setString(2, elev);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				marks.add(new Mark(rs.getInt(1), rs.getString(2)));

			}

			sql = "select to_date(data,'YY-MM-DD') from absente where id_materie = (select id from materie where nume=?) and id_elev = (select id from elevi where nume=?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, materie);
			pstmt.setString(2, elev);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				absences.add(new Absence(rs.getString(1)));
			}

			profesor = db.selectProfesorByMaterie(materie);
			subject = new Subject(materie, profesor, marks, absences);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return subject;
	}

	public Student selectRaportElev(String elev) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		Student student = null;
		try {
			Database db = new Database();
			ArrayList<String> materiiByElev = db.selectMateriiByElev(elev);
			for (String str : materiiByElev) {
				subjects.add(db.selectRaportElevByMaterie(elev, str));
			}

			student = new Student(elev, subjects);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} // end try

		System.out.println("Goodbye!");
		return student;
	}

	public static void main(String[] args) throws SQLException {
		Database db = new Database();
		// System.out.println(db.selectProfesorByMaterie("Matematica"));
		// db.insertNotaElevWithDate("Teodor Sposib",
		// "Matematica",10,"2017-05-11 00:00:00.0");
		Student student = db.selectRaportElev("Teodor Sposib");
		XMLConvert xml = new XMLConvert();
		//xml.convertRaporStudentToXML(student);
		//xml.convertStudentsToXML();
		// db.insertNotaElev("Teodor Sposib", "Matematica",10);

		// xml.convertStudentsToXML();
		// StudentsWrapper sw =
		xml.insertStudentsFromXML("C:\\Users\\Bogdan\\Desktop\\file.xml");
		// System.out.println(sw.getStudents().get(0).getName());
		// for(Student s : sw.getStudents()){
		// System.out.println(s.getName());
		// }
		// System.out.println(db.selectNumeElevi());
		// db.insertProfesor("Gigel","gigel","Matematica");
		// System.out.print(db.checkAccount("Gigel","gigel","profesor"));
	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());
		}
		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}