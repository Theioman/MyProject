package com.wenda.service;

import java.util.List;

import com.wenda.dao.AnswerDao;
import com.wenda.domain.Answer;
import com.wenda.domain.Users;


public class AnswerService {
	private static AnswerService ans=null;
	
	private AnswerService(){}
	
	public static AnswerService getInstance(){
		if(ans==null){
			ans=new AnswerService();
		}
		return ans;
	}
	AnswerDao asd=AnswerDao.getInstance();
	/**
	 * 发布回答
	 * @param content
	 * @param qid
	 * @param uid
	 * @return
	 */
	public Integer sendAnswer(String content,int qid,int uid) {
		return asd.sendAnswer(content,qid,uid);
	}
	/**
	 * 通过id查询所有回答
	 * @param id
	 * @return
	 */
	public List<Answer> findAllAnswer(int id) {
		return asd.findAllAnswer(id);
	}
	/**
	 * 删除回答
	 * @param id
	 * @param qid
	 * @return
	 */
	public Integer delAnswer(int id,int qid) {
		return asd.delAnswer(id,qid);
	}
	/*public void dianZan(int id){
		asd.dianZan(id);
	}*/
	/**
	 * 点赞
	 * @param id
	 * @return
	 */
	public Integer support(int id) {
		return asd.support(id);
	}
}
