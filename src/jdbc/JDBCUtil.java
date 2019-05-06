package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class JDBCUtil {

	public static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 如果发现where后面使用中文值的字段查询不到结果,就要在连接url后面加上?useUnicode=true&characterEncoding=UTF-8
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
