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

	//��������
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//��ȡ����
	private Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/practise", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//�ͷ���Դ
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
	
	//��Id��ȡ�û�����
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
				//������ڣ���ֱ�ӹ����������û�����
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
	
	//��ѯ�����û�
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
	
	//�޸��û�����
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
	
	//����IDɾ���û�
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
	
	//�����û�����
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
