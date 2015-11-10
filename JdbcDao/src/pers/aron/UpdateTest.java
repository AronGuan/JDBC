package pers.aron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//CONCUR_READ_ONLY:结果集不能用于更新数据库
//CONCUR_UPDATEBLE:结果集可以用于更新数据库

//可更新的结果集
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
			//更新一条记录
			rs.updateString(2, "Davin");
			rs.updateRow();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
