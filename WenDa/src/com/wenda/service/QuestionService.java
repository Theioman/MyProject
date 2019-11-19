package com.wenda.service;

import java.util.List;

import com.wenda.dao.QuestionDao;
import com.wenda.domain.Collection;
import com.wenda.domain.Question;
import com.wenda.domain.Users;

public class QuestionService {
private static QuestionService questionService;
private QuestionDao questionDao=QuestionDao.getInstance();
	private QuestionService() {
		
	}
	public static QuestionService getInstance() {
		if(questionService==null) {
			questionService=new QuestionService();
		}
		return questionService;
	}
	/**
	 * 查找所有问题
	 * @return
	 */
	public List<Question> findAllQuestion() {
		return questionDao.findAllQuestion();
	}
	/**
	 * 发布问题
	 * @param title
	 * @param content
	 * @param reward
	 * @param id
	 */
	public void sendQuestion(String title, String content, int reward,int id) {
		questionDao.sendQuestion(title,content,reward,id);
	}
	/**
	 * 查询我的问题
	 * @param id
	 * @return
	 */
	public List<Question> findMyQuestion(int id) {
		return questionDao.findMyQuestion(id);
	}
	/**
	 * 查找单个问题
	 * @param id
	 * @return
	 */
	public Question findquestion(int id){
		return questionDao.findquestion(id);
	}
	/**
	 * 删除问题
	 * @param id
	 */
	public void delquestion(int id) {
		questionDao.delquestion(id);
	}
	/**
	 * 收藏问题
	 * @param collection
	 */
	public void collections(Collection collection) {
		questionDao.collections(collection);
	}
	public boolean ifCollect(Collection collection) {
		return questionDao.ifCollection(collection);
	}

}

