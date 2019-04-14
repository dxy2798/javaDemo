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
    
	   public daoDerby() {                                    // 构造方法
	        try {
	            Class.forName("org.apache.derby.jdbc.ClientDriver"); // 加载数据库驱动
	        } catch (ClassNotFoundException e) {
	            JOptionPane.showMessageDialog(null, "数据库驱动加载失败，请将jdbc驱动配置到构建路径中。\n" + e.getMessage());
	        }
	    }
	    /**
	     * 获得数据库连接的方法
	     * 
	     * @return Connection
	     */
	    public static Connection getConn() {
	        try {
	            Connection conn = null; // 定义数据库连接
                //String url = "jdbc:derby:q:\\kerryeas";
	            String url = "jdbc:derby://localhost:1527/F:\\java_bak\\kerryeas"; // 数据库db_Express的URL
	            String username = "nick"; // 数据库的用户名
	            String password = "32147"; // 数据库密码
	            conn = DriverManager.getConnection(url, username, password); // 建立连接
	            System.out.println("Connection successly");
	            return conn; // 返回连接
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "数据库连接失败。\n请检查数据库用户名和密码是否正确。" + e.getMessage());
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
