package pers.aron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {

	//加载驱动
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//获取连接
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/practise", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//释放资源
	public static void release(ResultSet rs,Statement ps,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!= null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
