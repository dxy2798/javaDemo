package CalendarTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class EventClass {
	//Event 类 更新数据
	public String driver =
			"org.apache.derby.jdbc.ClientDriver";
			//"org.apache.derby.jdbc.EmbeddedDriver";
	
	private String username;
	private String password;
	
	
	public EventClass(){}
	
	private Connection getConnection() throws Exception{
		Class.forName(driver).newInstance();
		Connection conn = null;
		conn = DriverManager.getConnection(
				"jdbc:derby://localhost:1527/d:\\calendar;user="+
					username + ";password=" + password);
		
		return conn;
		
				
	}
	public EventClass(int eventId,String newUsername,
								     String newPassword){
		this.username = newUsername;
		this.password = newPassword;
		
		setId(eventId);
	}
	public EventClass(String newTitle,String newDescription,String newRemindersTo,
			int eventMonth,int eventDay,int eventYear,String newUsername,String newPassword){
		
		this.username = newUsername;
		this.password = newPassword;
		
		create(newTitle,newDescription,newRemindersTo,
				eventMonth,eventDay,eventYear);
		
	}
	public EventClass(String newTitle,String newDescription,
			String newRemindersTo,int eventMonth,int eventDay,
			int eventYear){
		create(newTitle,newDescription,newRemindersTo,eventMonth,
				eventDay,eventYear);
		
	}
	public EventClass(int eventId) {
		this.id = eventId;
	}
	private int eventDay;
	private int eventMonth;
	private int eventYear;
	
	public int getEventDay() {
		return eventDay;
	}

	public void setEventDay(int eventDay) {
		this.eventDay = eventDay;
	}

	public int getEventMonth() {
		return this.eventMonth;
	}

	public void setEventMonth(int eventMonth) {
		this.eventMonth = eventMonth;
	}

	public int getEventYear() {
		return this.eventYear;
	}

	public void setEventYear(int eventYear) {
		this.eventYear = eventYear;
	}

	private String description;
	
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private int id;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String remindersTo;
	
	public String getRemindersTo() {
		return this.remindersTo;
	}

	public void setRemindersTo(String remindersTo) {
		this.remindersTo = remindersTo;
	}

	private String title;
	
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean create(String newTitle,String newDescription,
			String newRemindersTo,int eventMonth,
			int eventDay,int eventYear){
		setTitle(newTitle);
		setDescription(newDescription);
		setRemindersTo(newRemindersTo);
		setEventMonth(eventMonth);
		setEventDay(eventDay);
		setEventYear(eventYear);
		System.out.println("Creating event for " + this.getEventMonth() +
				"/" + this.getEventDay() + "/" + this.getEventYear());
		System.out.println(this.getTitle());
		System.out.println(this.getDescription());
		System.out.println("Reminders to: " + this.getRemindersTo());
		try{
			Class.forName(driver).newInstance();
			Connection conn =null;
			conn=DriverManager.getConnection(
					"jdbc:derby://localhost:1527/d:\\calendar;user="
					+ this.username + ";password=" + this.password);
			conn.setAutoCommit(false);   //禁用自动提交模式
			PreparedStatement ps = conn.prepareStatement(    //预编译的带参数的SQL语句
					"insert into Event (title, description," +
					"remindersTo, eventMonth, " +
					"eventDay, eventYear)" +
					"values (?,?,?,?,?,?)");
			ps.setString(1, this.getTitle());
			ps.setString(2, this.getDescription());
			ps.setString(3, this.getRemindersTo());
			ps.setInt(4, eventMonth);
			ps.setInt(5, eventDay);
			ps.setInt(6, eventYear);
			ps.executeUpdate();
			System.out.println("Record inserted");
			ps.close();
			conn.commit();   //使得所有的更改,因为之前的提交/回滚永久和释放任何数据库锁目前由连接对象。应该使用这种方法只有当自动提交模式被禁用。
			conn.close();
			/*
			String sql = "insert into Event (title,description," +
					"remindersTo,eventMonth,eventDay,eventYear)" +
					"values ('" + this.getTitle() + "','"
					+ this.getDescription() + "','"
					+ this.getRemindersTo() + "','"
					+ this.getEventMonth() + "','"
					+ this.getEventDay() + "','"
					+ this.getEventYear() + ")";
					
			System.out.println(sql);
			Statement s = conn.createStatement();
			s.execute(sql);
			s.close();
			conn.close();*/
			try{
				DriverManager.getConnection(
						"jdbc:derby:;shutdown=true");
			}catch (Exception se){}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	public boolean executeUpdate(String newTitle,String newDescription,
            String newRemindersTo,int newMonth,int newDay,
            int newYear){
		try{
			Class.forName(driver).newInstance();
			Connection conn = null;
			conn = DriverManager.getConnection(
					"jdbc:derby://localhost:1527/d:\\calendar;user="
					+ username + ";password=" + password);
			conn.setAutoCommit(false);
			Statement s = conn.createStatement();
			String sql = "select * from Event where id= " +
					this.getId() + " for update of title,description, " +
					"remindersto,eventmonth,eventday,eventyear";
		    ResultSet updateRs = s.executeQuery(sql);	
		    PreparedStatement ps = conn.prepareStatement(
		    		"update event set title=?, " +
		            "description=?, remindersTo=?, eventMonth=? "+
		    		"eventDay=?, eventYear=? where current of UPDATEABLESTATEMENT");
		    if (updateRs.next()){
		    	ps.setString(1, newTitle);
		    	ps.setString(2, newDescription);
		    	ps.setString(3, newRemindersTo);
		    	ps.setInt(4, newMonth);
		    	ps.setInt(5, newDay);
		    	ps.setInt(6, newYear);
		    	ps.executeUpdate();
		    	System.out.println("Record updated");
		    }else{
		    	System.out.println("No such event");
		    }
		    updateRs.close();
		    ps.close();
		    s.close();
		    conn.commit();
		    conn.close();
		    try{
		    	DriverManager.getConnection(
		    			"jdbc:derby:shutdown=true");
		    }catch (SQLException se){}		    
		    
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean update(String newTitle,String newDescription,
			             String newRemindersTo,int newMonth,int newDay,
			             int newYear){
		try{
			Class.forName(driver).newInstance();
			Connection conn = null;
			conn = DriverManager.getConnection(
					"jdbc:derby://localhost:1527/d:\\calendar;user="
					+ username + ";password=" + password);
			//conn.setAutoCommit(false);
			Statement s = conn.createStatement();
			String sql = "select * from Event where id=" +
					this.getId() + " for update of title,description, " +
					"remindersto, eventmonth, eventday, eventyear";
			System.out.println(sql);
		    ResultSet updataRs = s.executeQuery(sql);
		    PreparedStatement ps = conn.prepareStatement(
		    		"update event set title=?, " +
		    		"description=?, " +
		    		"remindersTo=?, " +
		    		"eventMonth=?, eventDay=?, " +
		    		"eventYear=? where current of " +
		    		"UPDATEABLESTATEMENT");
		    
		    if (updataRs.next()){
		    	ps.setString(1, newTitle);
		    	ps.setString(2, newDescription);
		    	ps.setString(3, newRemindersTo);
		    	ps.setInt(4, newMonth);
		    	ps.setInt(5, newDay);
		    	ps.setInt(6, newYear);
		    	ps.executeUpdate();
		    	System.out.println("Record update");
		    }else{
		    	System.out.println("No such event");
		    }
		    
		    updataRs.close();
		    ps.close();
		    s.close();
		    conn.commit();
		    conn.close();
		    
		    try{
		    	DriverManager.getConnection(
		    			"jdbc:derby:shutdown=true");
		    }catch (SQLException se){}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	public boolean delete(){
		try{
			Class.forName(driver).newInstance();
			Connection conn = null;
			conn = DriverManager.getConnection(
					"jdbc:derby://localhost:1527/d:\\calendar;user="
					+ username + ";password=" + password);
			Statement s = conn.createStatement();
			String sql = "delete from Event where id= " + this.getId();
			System.out.println(sql);
			int rowDeleted = s.executeUpdate(sql);
			
			System.out.println(rowDeleted + " record(s) deleted");
			
			s.close();
			conn.close();
			try{
				DriverManager.getConnection(
						"jdbc:derby:;shutdown=true");
			}catch (SQLException se){}

		}catch(Exception e){
			e.printStackTrace();
		}

		return true;
	}
	public static void main(String[] args) {
		if (args.length<7){
			System.out.println("Usage: Event <eventId> <title> " +
			           "<description> <remindersTo> "+
					   "<month> <day> <year>") ;
		}else{
			int eventId = Integer.parseInt(args[0]);
			String newTitle = args[1];
			String newDescription = args[2];
			String newReminders = args[3];
			int newMonth = Integer.parseInt(args[4]);
			int newDay = Integer.parseInt(args[5]);
			int newYear = Integer.parseInt(args[6]);
			//Event theEvent = new Event(newTitle,newDescription,newReminders,
			//		newMonth,newDay,newYear);
			EventClass theEventToUpdate = new EventClass(eventId);
			theEventToUpdate.update(newTitle, newDescription,
					newReminders, newMonth, newDay, newYear);
		}

	}

}
