package CalendarTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ConnTest {
	public String driver =
			"org.apache.derby.jdbc.ClientDriver";
			
	
	private String username = "nickscalendar";
	private String password = "mypass";
	
	
	public ConnTest(){}
	
	private Connection getConnection() throws Exception{
		Class.forName(driver).newInstance();
		Connection conn = null;
		conn = DriverManager.getConnection(
				"jdbc:derby://localhost:1527/f:\\calendar;user="+
					username + ";passwword=" + password);
		System.out.println("连接成功");
		return conn;
		
				
	}
	public static void main(String[] args) {
		
		ConnTest con = new ConnTest();
		try {
			con.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
