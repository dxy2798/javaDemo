package CalendarTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.Message;

public class Reminder {
	// Reminder 类来用数据发 送电子邮件
	public static String driver = "org.apache.derby.jdbc.ClientDriver";
	public void sendMessage(String reminderTo,String title,
			String description,String dateString){
		try{
			String smtp = "smtp.163.com";
			String from = "dxy2798@163.com";
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtp);
			Session session = Session.getInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reminderTo));
			message.setSubject(title);
			String bodyText = "Just to reminder for you of the " +
					"following event to take place on " +
					dateString + ":\n\n";
			bodyText = bodyText + description;
			message.setText(bodyText);
			Transport.send(message);
	
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendAllReminders(int eventMonth,
			int eventDay,int eventYear,String username,String password){
		
		try{
			
			Class.forName(driver).newInstance();
			
			Connection conn = null;
			
			conn = DriverManager.getConnection(
					"jdbc:derby://localhost:1527/d:\\calendar;user=" +
					username + ";password=" + password);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Event " + 
					"where eventMonth=" + eventMonth +
					" and eventDay=" + eventDay + " and " +
					"eventYear=" + eventYear);
			while(rs.next()){
				String title = rs.getString(2);
				String description = rs.getString(3);
				String remindersTo = rs.getString(4);
				String dateString = rs.getString(5) + "/"
						+ rs.getString(6) + "/" + rs.getString(7);
				sendMessage(remindersTo,title,description,dateString);

			}
			rs.close();
			s.close();
			conn.close();
			try{
				DriverManager.getConnection(
						"jdbc:derby:;shutdown=true");
			}catch(SQLException se){}

		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
		
		Reminder rem = new Reminder();
		rem.sendMessage("StephenDeng@kerryeas.com","Test event",
					"Test description","8/26/2005");
		rem.sendAllReminders(2, 27, 2006,args[0],args[1]);
	}

}
