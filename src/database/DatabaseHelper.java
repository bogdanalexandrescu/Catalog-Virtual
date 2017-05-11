package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
	static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String DB_USER = "SYSTEM";
	static final String DB_PASSWORD = "alexbogdan";

	private DatabaseHelper() {

	}

	private static DatabaseHelper dbHelper = null;
	private static Connection con = null;

	public static DatabaseHelper getInstance() {
		if (dbHelper == null) {
			dbHelper = new DatabaseHelper();

			try {
				Class.forName(DB_DRIVER);
				con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return dbHelper;
	}

	public Connection getConnection(){
		return con;
	}
}
