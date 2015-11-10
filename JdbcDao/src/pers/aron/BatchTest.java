package pers.aron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//������
public class BatchTest {

	public void exeBatch(){
		String sql = "insert into user values(?,?,?)";
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = Utils.getConnection();
			//�ر��Զ��ύ
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setLong(1, 3);
			ps.setString(2, "rui");
			ps.setString(3, "m");
			ps.addBatch();
			
			ps.setLong(1, 4);
			ps.setString(2, "marry");
			ps.setString(3, "f");
			ps.addBatch();
			//ִ��������̬��SQL
			ps.addBatch("delete from user where id=2");
			
			ps.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			Utils.release(null, ps, conn);
		}
	}
}
