package CalendarTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Calendar {
	//Calendar 类来显示数据
	public static String driver =
			"org.apache.derby.jdbc.ClientDriver";
			//"org.apache.derby.jdbc.EmbeddedDriver";
	public static EventClass[] getEvents(int eventMonth,
			int eventDay,int eventYear,String username,String password){
		try{
			Connection conn = getConnection(username,password);
			Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT * FROM Event " +
						"where eventMonth=" + eventMonth + " and eventDay=" + eventDay + " and " +
						"eventYear=" + eventYear);
			EventClass[] events = null;
			if (!rs.next()){
				return null;
			}else{
				rs.last();
				int numberOfEvents = rs.getRow();
				rs.beforeFirst();
				events = new EventClass[numberOfEvents];
				int thisEventIndex = 0;
				EventClass thisEvent = null;
			while (rs.next()){
				thisEvent = new EventClass(rs.getInt(1),username,password);
				thisEvent.setTitle(rs.getString(2));
				thisEvent.setDescription(rs.getString(3));
				thisEvent.setRemindersTo(rs.getString(4));
				thisEvent.setEventMonth(rs.getInt(5));
				thisEvent.setEventDay(rs.getInt(6));
				thisEvent.setEventYear(rs.getInt(7));
				events[thisEventIndex] = thisEvent;  //这句要琢磨
				thisEventIndex = thisEventIndex + 1;
			  }
			
			}
			rs.close();
			s.close();
			conn.close();
			try{
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException se){}
			return events;
		}catch (Exception e){
			
			e.printStackTrace();
			return null;
		}

	}
	
	
	private static Connection getConnection(String username,
					String password) throws Exception{
				
			Class.forName(driver).newInstance();
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/d:\\calendar;user="
								+ username + ";password=" + password);
			return conn;
			}


	public static void main(String[] args) {
//		int month = Integer.parseInt(args[0]);
//		int day = Integer.parseInt(args[1]);
//		int year = Integer.parseInt(args[2]);
//		getEvents(month,day,year,args[3],args[4]);

	}

}
