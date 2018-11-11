package trainingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteStoring {

	public Connection getConnection(String s) throws Exception {
		return DriverManager.getConnection(s);
	}

	public void createTable() {

	}

	public void insertInto(Connection conn, String tablename, String[] values) throws Exception {

		String statement = "insert into " + tablename + " values (?, ?, ?, ?, ?, ?);";
		PreparedStatement prep = conn.prepareStatement(statement);

		prep.setString(1, values[0]);
		prep.setString(2, values[1]);
		prep.setString(3, values[2]);
		prep.setString(4, values[3]);
		prep.setString(5, values[4]);
		prep.setString(6, values[5]);
		prep.addBatch();

		conn.setAutoCommit(false);
		prep.executeBatch();
		conn.setAutoCommit(true);

	}

	public void selectFrom(Connection conn, String tablename, String[] items) throws Exception {

		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery("select * from " + tablename + ";");
		while (rs.next()) {
			System.out.println("Zutat:");
			System.out.println("Name: " + rs.getString(items[0]));
			System.out.println("Menge " + rs.getString(items[1]));
			System.out.println("Kalorien: " + rs.getString(items[2]));
			System.out.println("Fett: " + rs.getString(items[3]));
			System.out.println("Kohlenhydrate: " + rs.getString(items[4]));
			System.out.println("Eiweiﬂ: " + rs.getString(items[5]));

		}
		rs.close();
		conn.close();

	}

}
