package com.pasali.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MsgDAO {

	public static final String TABLE_MSG = "TextMsg";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NO = "number";
	public static final String COLUMN_BODY = "body";
	private static final String DATABASE_NAME = "MessagesDB";
	private Connection c = null;
	private Statement stmt = null;

	public void connectDatabase() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME + ".db");
	}

	public void create() {

		try {
			connectDatabase();
			stmt = c.createStatement();
			String DATABASE_CREATE = "create table " + TABLE_MSG + "("
					+ COLUMN_ID + " integer primary key autoincrement, "
					+ COLUMN_NO + " text not null, " + COLUMN_BODY
					+ " text not null" + ");";
			stmt.executeUpdate(DATABASE_CREATE);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void addMsg(Message msg) throws SQLException, ClassNotFoundException {
		connectDatabase();
		stmt = c.createStatement();
		String sql = "INSERT INTO " + TABLE_MSG + " (" + COLUMN_NO + ", "
				+ COLUMN_BODY + ") " + "VALUES (" + msg.getNo() + ", '"
				+ msg.getBody() + "')";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();

	}

	public Message getMsg(int id) {
		Message msg = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_MSG
					+ " where _id =" + String.valueOf(id) + ";");
			String body = rs.getString(COLUMN_BODY);
			String no = rs.getString(COLUMN_NO);
			msg = new Message(no, body);

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return msg;
	}

	public HashMap<String, String> getAllMsg() {

		HashMap<String, String> msgs = new HashMap<>();	
		try {
			connectDatabase();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " +TABLE_MSG + ";");
			while (rs.next()) {
				int id = rs.getInt(COLUMN_ID);
				String no = rs.getString(COLUMN_NO);
				msgs.put(no, String.valueOf(id));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return msgs;
	}
}
