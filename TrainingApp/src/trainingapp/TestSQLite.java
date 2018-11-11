package trainingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSQLite {

	public static void insertInto(Connection conn, String tablename, String[] values) throws Exception {

		String statement = "insert into " + tablename + " values (?, ?, ?, ?, ?, ?);";
		PreparedStatement prep = conn.prepareStatement(statement);

		// mit for Schleife

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

	public static void selectFrom(Connection conn, String tablename, String[] items) throws Exception {

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

	public static void main(String[] args) throws Exception {
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
		Statement stat = conn.createStatement();

		// stat.executeUpdate("drop table if exists zutaten;");

		// stat.executeUpdate("create table if not exists zutaten (name, menge,
		// kalorien, fett, carbs, eiweiss);");

		String[] neueZutat = new String[6];
		neueZutat[0] = "H‰‰‰hnchen";
		neueZutat[1] = "100";
		neueZutat[2] = "145";
		neueZutat[3] = "0.0";
		neueZutat[4] = "6.2";
		neueZutat[5] = "22.2";

		// insertInto(conn, "zutaten", neueZutat);

		String[] items = new String[6];
		items[0] = "name";
		items[1] = "menge";
		items[2] = "kalorien";
		items[3] = "fett";
		items[4] = "carbs";
		items[5] = "eiweiss";

		selectFrom(conn, "zutaten", items);

	}
}