package database;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import entities.Absence;
import entities.Mark;
import entities.Student;
import entities.StudentsWrapper;
import entities.Subject;
import server.utilities.XMLConvert;

public class Database {
	// JDBC driver name and database URL
	static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

	// Database credentials
	static final String DB_USER = "SYSTEM";
	static final String DB_PASSWORD = "alexbogdan";

	public void insertElevi(String nume) throws SQLException {
		Database db = new Database();
		if (!db.selectNumeElevi().contains(nume)) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {

				conn = DatabaseHelper.getInstance().getConnection();
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
			} // end try
		}
	}

	public void insertMaterie(String nume) throws SQLException {
		Database db = new Database();
		if (!db.selectNumeMaterii().contains(nume)) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				// STEP 2: Register JDBC driver

				// STEP 3: Open a connection
				conn = DatabaseHelper.getInstance().getConnection();
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
			} // end try

		}
	}

	public void insertProfesor(String nume, String parola, String materie) throws SQLException {
		Database db = new Database();
		if (!db.selectNumeProfesori().contains(nume))

			if (db.selectNumeMaterii().contains(materie)) {

				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					// STEP 2: Register JDBC driver

					// STEP 3: Open a connection

					conn = DatabaseHelper.getInstance().getConnection();
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
				} // end try
			}
	}

	public void insertElevMaterie(String numeElev, String materie) throws SQLException {
		Database db = new Database();
		if (db.selectNumeElevi().contains(numeElev) && db.selectNumeMaterii().contains(materie)
				&& !db.selectEleviByMaterie(materie).contains(numeElev)) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DatabaseHelper.getInstance().getConnection();
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
			} // end try
		}
	}

	public void insertNotaElevWithDate(String nume, String materie, int nota, String data) throws SQLException {
		Database db = new Database();
		if (db.selectNumeElevi().contains(nume) && db.selectNumeMaterii().contains(materie)) {
			boolean exist = false;
			for (Mark m : db.selectRaportElevByMaterie(nume, materie).getMarks())
				if (m.getData().equals(data)) {
					exist = true;
					break;
				}
			if (exist == false) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					conn = DatabaseHelper.getInstance().getConnection();

					String sql = "INSERT INTO Note (ID,ID_ELEV,ID_MATERIE,NOTA,DATA)" + "VALUES"
							+ "(seq_note.nextval,(select id from elevi where nume=?),(select id from materie where nume = ? ),?,TO_DATE(?,'DD/MM/YYYY'))";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, nume);
					pstmt.setString(2, materie);
					pstmt.setInt(3, nota);
					pstmt.setString(4, data);
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
				} // end try
					// System.out.println("Goodbye!");
			}
		}
	}

	public void insertAbsentaElevWithDate(String nume, String materie, String data) throws SQLException {
		Database db = new Database();
		if (db.selectNumeElevi().contains(nume) && db.selectNumeMaterii().contains(materie)) {
			boolean exist = false;
			for (Absence m : db.selectRaportElevByMaterie(nume, materie).getAbsences())
				if (m.getData().equals(data)) {
					exist = true;
					break;
				}
			if (exist == false) {

				Connection conn = null;
				PreparedStatement pstmt = null;
				try {

					conn = DatabaseHelper.getInstance().getConnection();
					String sql = "INSERT INTO ABSENTE (ID,ID_ELEV,ID_MATERIE,DATA)" + "VALUES"
							+ "(seq_absente.nextval,(select id from elevi where nume=?),(select id from materie where nume = ? ),TO_DATE(?,'DD/MM/YYYY'))";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, nume);
					pstmt.setString(2, materie);
					pstmt.setString(3, data);
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
				} // end try
			}
		}
	}

	public boolean checkAccount(String nume, String parola, String rol) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select id from accounts where nume=? and parola=? and rol=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, parola);
			pstmt.setString(3, rol);
			rs = pstmt.executeQuery();
			if (rs.next())
				check = true;

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
			if (rs != null)
				rs.close();
		} // end try

		return check;
	}

	public String selectMaterieByProfesor(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String materie = "";
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "select nume from materie where id=(select id_materie from accounts where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				materie = rs.getString("NUME");
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
			if (rs != null)
				rs.close();
		} // end try

		return materie;
	}

	public ArrayList<String> selectNumeElevi() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> numeElevi = new ArrayList<String>();
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select nume from elevi";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
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
			if (rs != null)
				rs.close();
		} // end try

		Collections.sort(numeElevi);
		return numeElevi;
	}

	public ArrayList<String> selectNumeProfesori() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> numeProfesori = new ArrayList<String>();
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select nume from accounts where rol='profesor'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
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
			if (rs != null)
				rs.close();
		} // end try

		Collections.sort(numeProfesori);
		return numeProfesori;
	}

	public String selectProfesorByMaterie(String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String profesor = null;
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select nume from accounts where id_materie = (select id from materie where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, materie);
			rs = pstmt.executeQuery();
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
			if (rs != null)
				rs.close();
		} // end try

		return profesor;
	}

	public ArrayList<String> selectNumeMaterii() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> numeMaterii = new ArrayList<String>();
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select nume from materie";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
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
			if (rs != null)
				rs.close();
		} // end try

		Collections.sort(numeMaterii);
		return numeMaterii;
	}

	public ArrayList<String> selectMateriiByElev(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ArrayList<String> numeMaterii = new ArrayList<String>();
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select id_materie from elev_materie where id_elev = (select id from elevi where nume=?)";
			String sql2 = "select nume from materie where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setString(1, nume);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pstmt2.setLong(1, rs.getInt(1));
				rs2 = pstmt2.executeQuery();
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
			if (rs != null)
				rs.close();
			if (rs2 != null)
				rs2.close();
		} // end try

		Collections.sort(numeMaterii);
		return numeMaterii;
	}

	public ArrayList<String> selectEleviByMaterie(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ArrayList<String> numeElevi = new ArrayList<String>();
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select id_elev from elev_materie where id_materie = (select id from materie where nume=?)";
			String sql2 = "select nume from elevi where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setString(1, nume);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pstmt2.setLong(1, rs.getInt(1));
				rs2 = pstmt2.executeQuery();
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
			if (rs != null)
				rs.close();
			if (rs2 != null)
				rs2.close();
		} // end try

		Collections.sort(numeElevi);
		return numeElevi;
	}

	public Subject selectRaportElevByMaterie(String elev, String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<Absence> absences = new ArrayList<Absence>();
		ArrayList<Mark> marks = new ArrayList<Mark>();
		Subject subject = null;
		String profesor = null;
		ResultSet rs = null;
		try {

			Database db = new Database();

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "select nota,to_char(data, 'dd/mm/yyyy') from note where id_materie = (select id from materie where nume=?) and id_elev = (select id from elevi where nume=?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, materie);
			pstmt.setString(2, elev);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				marks.add(new Mark(rs.getInt(1), rs.getString(2)));
			}

			sql = "select to_char(data, 'dd/mm/yyyy') from absente where id_materie = (select id from materie where nume=?) and id_elev = (select id from elevi where nume=?)";
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
			if (rs != null)
				rs.close();
		} // end try

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
		} // end try

		return student;
	}

	public void deleteProfesor(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "DELETE from NOTE where ID_MATERIE = (Select id_Materie from accounts where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.executeUpdate();
			sql = "DELETE from ABSENTE where ID_MATERIE = (Select id_Materie from accounts where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.executeUpdate();
			// }

			sql = "DELETE from accounts where nume=?";
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
			if (rs != null)
				rs.close();
		} // end try
	}

	public void deleteElev(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();
			String sql = "DELETE from NOTE where ID_Elev = (Select id from elevi where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.executeUpdate();
			sql = "DELETE from ABSENTE where ID_ELEV = (Select id from elevi where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.executeUpdate();
			sql = "DELETE from ELEV_MATERIE where ID_ELEV = (Select id from elevi where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.executeUpdate();

			sql = "DELETE from elevi where nume=?";
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
			if (rs != null)
				rs.close();
		} // end try
	}

	// merge doar daca materie nu este asignata unui profesor sau unui elev
	public void deleteMaterie(String nume) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean condition1 = false;
		boolean condition2 = false;
		try {
			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "SELECT ID FROM ACCOUNTS WHERE ID_MATERIE=(SELECT ID FROM MATERIE WHERE NUME=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			rs = pstmt.executeQuery();
			if (rs.next() == false)
				condition1 = true;

			sql = "SELECT ID FROM ELEV_MATERIE WHERE ID_MATERIE=(SELECT ID FROM MATERIE WHERE NUME=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			rs = pstmt.executeQuery();
			if (rs.next() == false)
				condition2 = true;

			if (condition1 && condition2) {
				sql = "DELETE from MATERIE where nume=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, nume);
				pstmt.executeUpdate();
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
			if (rs != null)
				rs.close();
		} // end try
	}

	public void updateNumeElev(String numeVechi, String numeNou) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "UPDATE ELEVi set Nume=? where nume=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, numeNou);
			pstmt.setString(2, numeVechi);
			pstmt.executeUpdate();
			// }

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
			if (rs != null)
				rs.close();
		} // end try
	}

	public void updateNumeProfesor(String numeVechi, String numeNou) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "UPDATE ACCOUNTS set Nume=? where nume=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, numeNou);
			pstmt.setString(2, numeVechi);
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
			if (rs != null)
				rs.close();
		} // end try

	}

	public void updateNumeMaterie(String numeVechi, String numeNou) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "UPDATE MATERIE set Nume=? where nume=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, numeNou);
			pstmt.setString(2, numeVechi);
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
			if (rs != null)
				rs.close();
		} // end try
	}

	public void deleteNotaElev(String nume, String materie, String data) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "DELETE from NOTE where ID_ELEV = (select id from elevi where nume=?) and ID_MATERIE = (select id from materie where nume=?) and data=(to_date(?,'DD/MM/YYYY'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			pstmt.setString(3, data);
			int n = pstmt.executeUpdate();

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

		} // end try
	}

	public void deleteAbsentaElev(String nume, String materie, String data) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "DELETE from ABSENTE where ID_ELEV = (select id from elevi where nume=?) and ID_MATERIE = (select id from materie where nume=?)  and data=(to_date(?,'DD/MM/YYYY'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			pstmt.setString(3, data);
			int n = pstmt.executeUpdate();

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

		} // end try
	}

	public void deleteMaterieElev(String nume, String materie) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DatabaseHelper.getInstance().getConnection();

			String sql = "DELETE from ELEV_MATERIE where ID_Elev = (Select id from elevi where nume=?) and ID_MATERIE = (select id from materie where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			int n = pstmt.executeUpdate();
			sql = "DELETE from ABSENTE where ID_ELEV = (Select id from elevi where nume=?) and ID_MATERIE = (select id from materie where nume=?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);

			sql = "DELETE from NOTE where ID_ELEV = (Select id from elevi where nume=?) and ID_MATERIE = (select id from materie where nume=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nume);
			pstmt.setString(2, materie);
			n = pstmt.executeUpdate();

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
			if (rs != null)
				rs.close();
		} // end try

	}
}