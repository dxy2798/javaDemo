package CalendarTest;

import javax.swing.*;

import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IpTestFrame extends JFrame{

	public String driver =
			"org.apache.derby.jdbc.ClientDriver";
	
	String[] s = { "西瓜", "苹果", "草莓", "香蕉", "葡萄" };

	private Component ipaddress;
	
	public IpTestFrame(){};
	public IpTestFrame(String FrameName){
		super(FrameName);
		this.setSize(800,450);
		this.getContentPane().setLayout(new java.awt.GridLayout(2, 1));
		JPanel title = new JPanel();
		title.add(new JLabel("<html><h2> IP地址查询</h2></html>"));
		this.add(title);
		int ipaddsIndex = 0;
		
		JPanel ip = new JPanel();
		try{
		  Connection conn = new IpTestFrame().getConnection();
			Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT * FROM Ip_Address");  //DISTINCT 
			if (!rs.next()){

			}else{
				rs.last();
				ipaddsIndex = rs.getRow();
				System.out.println(ipaddsIndex);
				System.out.println(rs.getMetaData().getColumnCount());
				rs.beforeFirst();
				List ipadds = new List();
				
				for(int i=0;i<rs.getRow();i++){
					rs.absolute(i+1);
					ipadds.add(rs.getString(1));
					
					System.out.println(rs.getString(5));
				}
					
				
				//int thisEventIndex = 0;
//			int index = 0;
//			while (rs.next()){
//				ipadds[index] = rs.getString(5);
//				index = index + 1;
//			}
//			JComboBox ipaddress = new JComboBox(ipadds);
			rs.close();
			s.close();
			conn.close();
			}
		}catch (Exception e) {
	
			e.printStackTrace();
		}
		
		
		
		ip.add(ipaddress);
		//this.add(ip);
		this.setVisible(true);
	}
	
	//获得数据连接
	private Connection getConnection() throws Exception{
		try{
		Class.forName(driver).newInstance();
		Connection conn = null;
		conn = DriverManager.getConnection(       //K:\workspace0909备份\calendar
				"jdbc:derby://localhost:1527/e:\\calendar;user=nickscalendar"+
				
					 ";password=mypass");
//		"jdbc:derby://localhost:1527/d:\\calendar;user=nickscalendar"+
		return conn;
		}catch (Exception e){e.printStackTrace();}
		return null;
	}

	public static void main(String[] args) {
		
		IpTestFrame w = new IpTestFrame("IP地址查询");
		w.setLocationRelativeTo(null);

	}

}
