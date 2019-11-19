package com.wenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wenda.domain.Answer;
import com.wenda.domain.Question;
import com.wenda.domain.Users;
import com.wenda.util.DBUtil;

/**
 * 回答评论的dao
 * @author hp
 *
 */
public class AnswerDao {
	
	public static void main(String[] args) {
		/*new AnswerDao().sendAnswer("12345678");*/
		List<Answer> l=new AnswerDao().findAllAnswer(1);
		System.out.println(l.size());
	}
	private static AnswerDao asd=null;
	
	private AnswerDao(){}
	
	public static AnswerDao getInstance(){
		if(asd==null){
			asd=new AnswerDao();
		}
		return asd;
	}
	static{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 通过id查询所有的回答评论
	 * @param id
	 * @return
	 */
	public List<Answer> findAllAnswer(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con = DBUtil.getConnection();
			ps=con.prepareStatement("select a.*,u.username,u.id uids,q.id qid,q.anum from question q,answer a,users u where a.u_id=u.id and q.id=a.q_id and q.id=? order by a.id desc");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			List<Answer> list=new ArrayList<>();
			while(rs.next()) {
				Answer ans=new Answer();
				Users user =new Users();
				Question question=new Question();
				question.setAnum(rs.getInt("anum"));
				question.setId(rs.getInt("qid"));
				ans.setId(rs.getInt("id"));
				ans.setContent(rs.getString("content"));
				ans.setTime(rs.getDate("time"));
				ans.setSupport(rs.getInt("support"));
				user.setUsername(rs.getString("username"));
				user.setId(rs.getInt("uids"));
				ans.setUser(user);
				ans.setQuestion(question);
				list.add(ans);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	/*public void dianZan(int id){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.25.164.136:1521:orcl", "scott", "cting");
			ps=con.prepareStatement("update answer set support=support+1 where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	/**
	 * 发布回答
	 * @param content
	 * @param qid
	 * @param uid
	 * @return
	 */
	public Integer sendAnswer(String content,int qid,int uid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String sql="insert into answer(id,content,time,q_id,u_id) values(answer_seq.nextval,?,sysDate,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, qid);
			ps.setInt(3, uid);
			if(ps.executeUpdate()>0) {
				ps=con.prepareStatement("update question set anum=anum+1 where id=?");
				ps.setInt(1, qid);
				if(ps.executeUpdate()>0) {
					ps=con.prepareStatement("select anum from question where id=?");
					ps.setInt(1, qid);
					rs=ps.executeQuery();
					if(rs.next()) {
						return rs.getInt("anum");
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(con, ps, rs);
		}
		return null;
	}
	/**
	 * 删除回答
	 * @param id
	 * @return 
	 */
	public Integer delAnswer(int id,int qid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String sql="delete from answer where id=?";
		try{
			con =DBUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			if(ps.executeUpdate()>0) {
				ps=con.prepareStatement("update question set anum=anum-1 where id=?");
				ps.setInt(1, qid);
				if(ps.executeUpdate()>0) {
					ps=con.prepareStatement("select anum from question where id=?");
					ps.setInt(1, qid);
					rs=ps.executeQuery();
					if(rs.next()) {
						return rs.getInt("anum");
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(con, ps,rs);
		}
		return null;
	}
	/**
	 * 点赞
	 * @param id
	 * @return
	 */
	public Integer support(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBUtil.getConnection();
			ps=con.prepareStatement("update answer set support=support+1 where id=?");
			ps.setInt(1, id);
			if(ps.executeUpdate()>0) {
			ps=con.prepareStatement("select support from answer where id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("support");
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, ps, rs);
		}
		
		return null;
	}
}
