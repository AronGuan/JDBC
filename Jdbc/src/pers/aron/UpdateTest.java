package pers.aron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//CONCUR_READ_ONLY:������������ڸ������ݿ�
//CONCUR_UPDATEBLE:������������ڸ������ݿ�

//�ɸ��µĽ����
public class UpdateTest {

	public void update(){
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "select * from user";
		try{
			conn = Utils.getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			rs.next();
			//����һ����¼
			rs.updateString(2, "Davin");
			rs.updateRow();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
