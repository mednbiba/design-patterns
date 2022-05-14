package designPatern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	private static Connection conn;
	
	static {
		final String url = "jdbc:mysql://localhost:3306/test";
		final String user = "root";
		final String pass = "";
		try {
			conn = (Connection) DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Database Connection Created : Logged to " + url + " as : " + user );
		
		
	}
	public static Connection getCon() {
		return conn;
	}

}
