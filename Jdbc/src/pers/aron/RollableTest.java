package pers.aron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//TYPE_FORWARD_ONLY:��������ܹ���
//TYPE_SCROLL_INSENSITIVE:��������Թ��������Ƕ����ݿ�仯������
//TYPE_SCROLL_SENSITIVE:������Թ��������Ƕ����ݿ�仯����

//�ɹ����Ľ����
public class RollableTest {

	public void rollableResult(){
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "select * from user";
		try{
			conn = Utils.getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = ps.executeQuery();
			rs.next();  //�õ���һ�м�¼
			System.out.println(rs.getLong(1));
			
			rs.last();  //�������һ��
			System.out.println(rs.getLong(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			
			rs.previous(); //�ص�ǰһ��
			System.out.println(rs.getLong(1));
			
			rs.absolute(2);  //��λ���ڶ���
			System.out.println(rs.getLong(1));
			
		}catch(Exception e){
			e.printStackTrace();
			Utils.release(rs, ps, conn);
		}
	}
}
