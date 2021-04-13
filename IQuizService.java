package com.tyss.quizpro.service;

import com.tyss.quizpro.bean.Results;
import com.tyss.quizpro.bean.UserInfo;

public interface IQuizService {
	public UserInfo login();
	public UserInfo registration();
	
	public Object questions(String language);
	
	public Results result();
	
	public Results allExamResult();
}
