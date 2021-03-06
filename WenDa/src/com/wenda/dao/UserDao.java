package com.wenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wenda.domain.Users;
import com.wenda.util.DBUtil;

public class UserDao {

	private static UserDao userdao=null;
	
	private UserDao(){}
	
	public static UserDao getInstance(){
		if(userdao==null){
			userdao=new UserDao();
		}
		return userdao;
	}
//	static{
//		try{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	/**
	 * 登录
	 */
	private static String LOGIN="select * from users where username=? and password=?";
	public Users loginOf(String username, String password) {
		Users user=null;
		try{
//			con = DriverManager.getConnection("jdbc:oracle:thin:@10.25.164.136:1521:orcl", "scott", "cting");
			con=DBUtil.getConnection();
			ps=con.prepareStatement(LOGIN);
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				user =new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPoint(rs.getInt("point"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return user;
	}
	/**
	 * 注册
	 * @param user
	 */
	public void insertUser(Users user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into users(id,username,password,email,phone,point) values(users_seq.nextval,?,?,?,?,0)";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			// 给占位符赋值
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPhone());


			// 执行sql语句
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 查询所有用户
	 * @return
	 */
	public  List<Users> showExperts() {
		System.out.println("正在进入showExperts方法");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		List<Users> list= new ArrayList();

		String sql = "select username,image_path,money,grdae from users where rownum<=6 order by grdae desc";
//		conn = DriverManager.getConnection("jdbc:oracle:thin:@10.25.164.136:1521:orcl", "scott", "cting");
		try {
			conn =DBUtil.getConnection();
			stmt=conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Users user= new Users();
				user.setUsername(rs.getString("username"));
				user.setImage(rs.getString("image_path"));
				user.setMoney(rs.getInt("money"));
				user.setGrdae(rs.getString("grdae"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/*public void sendQuestion(String title, String content, int reward,int id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into question(id,title,content,reward,u_id) values(users_seq.nextval,?,?,?,?)";
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.25.164.136:1521:orcl", "scott", "cting");
			ps=con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, reward);
			ps.setInt(4, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	
}
