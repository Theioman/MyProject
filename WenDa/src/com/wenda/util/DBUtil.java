package com.wenda.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * 数据库连接池工具类
 * @author jh
 *
 */
public class DBUtil {
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:comp/env/jdbc/My12306");
			conn=ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 关闭连接对象和处理对象
	 * @param conn
	 * @param stmt
	 */
	public static void close(Connection conn,Statement stmt) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 关闭连接对象，处理对象和结果集对象
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection conn,Statement stmt,ResultSet rs) {
		close(conn,stmt);
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
