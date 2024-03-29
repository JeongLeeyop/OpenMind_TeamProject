package mind.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * DB연동을 위한 로드, 연결, 닫기 기능 클래스
 */
public class DbUtil {
	private static DataSource ds;
	private static Properties proFile = new Properties();

	/**
	 * 로드
	 */
    static {
		try {
			Class clazz = DbUtil.class;		
			proFile.load(clazz.getResourceAsStream("DbSql.properties"));
			
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/myoracle");// context.xml 파일에 name이랑 같은지 확인해주세요!
			System.out.println("ds : " + ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProFile() {
		
		return proFile;
	}

	/**
	 * 연결
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	/**
	 * 닫기 (insert, update, delete 인경우 )
	 */
	public static void dbClose(Statement st, Connection con) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 닫기(select 인경우)
	 */
	public static void dbClose(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null)
				rs.close();
			dbClose(st, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
