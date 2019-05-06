package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Test {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
    	Connection con = null;
    	Statement stat = null;
    	ResultSet rs = null;
    	try {
			con = JDBCUtil.getConnection();
			stat = (Statement) con.createStatement();
			// 查询数据库
			String sql = "select * from tb_film_info where f_name = '大话西游'";
			rs= stat.executeQuery(sql);			
			while (rs.next()) {				
				System.out.println(rs.getString("f_name")+"是"+rs.getString("f_type")+",播放时间是:"+rs.getInt("f_play_year")+"年");
			}
			
			// 插入一条记录
			sql = "insert into tb_film_info values(null,'无双','动作片',90,2018,'中国')";
			int insertNum = stat.executeUpdate(sql);
			System.out.println("数据库增加"+insertNum+"条记录");
			
			// 更新一条记录
			sql = "update tb_film_info set f_score = 95 where f_name = '无双'";
			// 是否有结果集
			boolean update = stat.execute(sql);
			if (update) {
				rs = stat.getResultSet();
			}else{
				System.out.println("在数据库更新"+stat.getUpdateCount()+"条记录");
			}
			
			// 删除一条记录
			sql = "delete from tb_film_info where f_name = '无双'";
			int deleteNum = stat.executeUpdate(sql);
			System.out.println("在数据库删除"+deleteNum+"条记录");
			
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (stat != null) {
					stat.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    }
}
