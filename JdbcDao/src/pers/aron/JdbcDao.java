package pers.aron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class JdbcDao {

	//加载驱动
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//获取连接
	private Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/practise", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//释放资源
	private void release(ResultSet rs,Statement ps,Connection conn){
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
	
	//用Id获取用户对象
	public User getUserById(long id){
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "select * from user where id = ?";
		try{
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				//如果存在，则直接构建并返回用户对象
				User user = new User(rs.getLong("id"),rs.getString("name"),rs.getString("gender"));
				return user;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			release(rs, ps, conn);
		}
		return null;
	}
	
	//查询所有用户
	public List<User> getAllUsers(){
		List<User> list = new ArrayList<User>();
		ResultSet rs =null;
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "select * from user";
		try{
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				User user = new User(rs.getLong("id"),rs.getString("name"),rs.getString("gender"));
				list.add(user);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			release(rs, ps, conn);
		}
		return list;
	}
	
	//修改用户数据
	public User updateUser(User user){
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "update user set name=?,gender=? where id=?";
		try{
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getGender());
			ps.setLong(3, user.getId());
			int rst = ps.executeUpdate();
			conn.commit();
			if(rst > 0){
				return user;
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			release(null, ps, conn);
		}
		return null;
	}
	
	//根据ID删除用户
	public boolean deleteUser(long id){
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "delete from user where id = ?";
		try{
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			int rst = ps.executeUpdate();
			conn.commit();
			if(rst > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			release(null, ps, conn);
		}
		return false;
	}
	
	//插入用户数据
	public User insertUser(User user){
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "insert into user values(?,?,?)";
		try{
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setLong(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getGender());
			int rst = ps.executeUpdate();
			conn.commit();
			if(rst > 0){
				return user;
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			release(null, ps, conn);
		}
		return null;
	}
}
