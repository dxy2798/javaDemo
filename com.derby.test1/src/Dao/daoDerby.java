package Dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class daoDerby {
	private static daoDerby dao = new daoDerby();
    
	   public daoDerby() {                                    // ���췽��
	        try {
	            Class.forName("org.apache.derby.jdbc.ClientDriver"); // �������ݿ�����
	        } catch (ClassNotFoundException e) {
	            JOptionPane.showMessageDialog(null, "���ݿ���������ʧ�ܣ��뽫jdbc�������õ�����·���С�\n" + e.getMessage());
	        }
	    }
	    /**
	     * ������ݿ����ӵķ���
	     * 
	     * @return Connection
	     */
	    public static Connection getConn() {
	        try {
	            Connection conn = null; // �������ݿ�����
                //String url = "jdbc:derby:q:\\kerryeas";
	            String url = "jdbc:derby://localhost:1527/F:\\java_bak\\kerryeas"; // ���ݿ�db_Express��URL
	            String username = "nick"; // ���ݿ���û���
	            String password = "32147"; // ���ݿ�����
	            conn = DriverManager.getConnection(url, username, password); // ��������
	            System.out.println("Connection successly");
	            return conn; // ��������
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܡ�\n�������ݿ��û����������Ƿ���ȷ��" + e.getMessage());
	            return null;
	        }
	    }
	    
	    public static boolean isExist(String tableName){
	    	boolean b = false;
	    	
	    	daoDerby dao = new daoDerby();
	    	Connection conn = daoDerby.getConn();
	    	try {
				DatabaseMetaData dbmd = conn.getMetaData();
				dbmd.getTables(conn.getCatalog(), "nick", tableName, "TABLE");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	return b;
	    }
	    public static void main(String[] args) {
	    	System.out.print(new daoDerby().isExist("username"));
	    }	
}
