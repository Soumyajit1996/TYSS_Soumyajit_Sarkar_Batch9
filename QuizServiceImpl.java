package com.tyss.quizpro.service;

import java.util.Scanner;

import com.tyss.quizpro.bean.Results;
import com.tyss.quizpro.bean.UserInfo;
import com.tyss.quizpro.dao.IQuizDao;
import com.tyss.quizpro.dao.QuizDaoimpl;
public class QuizServiceImpl implements IQuizService{

	Scanner sc=new Scanner(System.in);
	IQuizDao idao=new QuizDaoimpl();
	@Override
	public UserInfo login() {
		System.out.println("Enter the user_id(email_id)");
		String email=ValidationCode.emailValidation();
		
		System.out.println("Enter the password");
		String password=sc.next();
		
		return idao.login(email,password);
	}

	@Override
	public UserInfo registration() {	
		System.out.println("Enter your name");
		String name=ValidationCode.nameValidation();
				
		System.out.println("Enter the email");
		String email=ValidationCode.emailValidation();
		
		System.out.println("Enter your age");
		int age=ValidationCode.ageValidation();
		
		System.out.println("Enter the password");
		String password=sc.next();
		return idao.registration(name,age,email,password);
	}
	
	@Override
	public Object questions(String language) {
		return idao.questions(language);
	}

	@Override
	public Results result() {
		System.out.println("Which result you want to see");
		System.out.println("1.Java");
		System.out.println("2.Html");
		System.out.println("3.JS");
		int choice=sc.nextInt();
		switch (choice) {
		case 1:
			String language1="java";
			return idao.result(language1);
		case 2:
			String language2="html";
			return idao.result(language2);
		case 3:
			String language3="js";
			return idao.result(language3);
		default:
			System.out.println("Not possible");
			break;
		}
		return null;
	}

	@Override
	public Results allExamResult() {
		return idao.allExamResult();
	}
}
