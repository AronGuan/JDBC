package pers.aron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//TYPE_FORWARD_ONLY:结果集不能滚动
//TYPE_SCROLL_INSENSITIVE:结果集可以滚动，但是对数据库变化不敏感
//TYPE_SCROLL_SENSITIVE:结果可以滚动，但是对数据库变化敏感

//可滚动的结果集
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
			rs.next();  //得到第一行记录
			System.out.println(rs.getLong(1));
			
			rs.last();  //来到最后一行
			System.out.println(rs.getLong(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			
			rs.previous(); //回到前一行
			System.out.println(rs.getLong(1));
			
			rs.absolute(2);  //定位到第二条
			System.out.println(rs.getLong(1));
			
		}catch(Exception e){
			e.printStackTrace();
			Utils.release(rs, ps, conn);
		}
	}
}
