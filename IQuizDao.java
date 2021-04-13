package com.tyss.quizpro.dao;

import com.tyss.quizpro.bean.ExamTopic;
import com.tyss.quizpro.bean.Results;
import com.tyss.quizpro.bean.UserInfo;

public interface IQuizDao {
	public UserInfo login(String email,String password);
	public UserInfo registration(String name,int age,String email,String password);

	public ExamTopic exam(String language);
	
	public Object questions(String language);
	
	public Results result(String language);
	
	public Results allExamResult();
}
