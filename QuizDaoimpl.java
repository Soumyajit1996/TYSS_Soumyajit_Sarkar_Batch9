package com.tyss.quizpro.dao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.tyss.quizpro.bean.ExamTopic;
import com.tyss.quizpro.bean.Options;
import com.tyss.quizpro.bean.Questions;
import com.tyss.quizpro.bean.Results;
import com.tyss.quizpro.bean.UserInfo;

public class QuizDaoimpl implements IQuizDao {

	int flag;
	static int uid;
	public static int count;
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	Scanner sc = new Scanner(System.in);
	Date date = new Date();

	public void entityMethod() {
		factory = Persistence.createEntityManagerFactory("QuizData");
		manager = factory.createEntityManager();
	}

	@Override
	public UserInfo login(String email, String password) {
		try {
			entityMethod();
			// to take dynamic values we have to go for jpql queries
			String data = "from UserInfo where email=:email1 and password=:password1";
			Query query = manager.createQuery(data);
			query.setParameter("email1", email);
			query.setParameter("password1", password);
			// to get multiple records we need to use query.getResultList()
			// and to get the single record we need to use query.getSingleRecord();
			UserInfo user = (UserInfo) query.getSingleResult();
			if (user != null) {
				uid = user.getUser_id();
				System.out.println("Login Successfully");
				count = 1;
			} else {
				count = -1;
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (factory != null) {
				factory.close();
			}
			if (manager != null) {
				manager.close();
			}
		}
		return null;
	}

	@Override
	public UserInfo registration(String name, int age, String email, String password) {

		UserInfo user = new UserInfo();
		user.setName(name);
		user.setAge(age);
		user.setEmail(email);
		user.setPassword(password);
		try {
			entityMethod();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			System.out.println("Data Inserted Successfully");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (factory != null) {
				factory.close();
			}
			if (manager != null) {
				manager.close();
			}
		}
		return null;
	}

	@Override
	public ExamTopic exam(String language) {
		try {
			entityMethod();
			String data = "from ExamTopic where exam_name=:language";
			Query query = manager.createQuery(data);
			query.setParameter("language", language);
			ExamTopic exam = (ExamTopic) query.getSingleResult();
			if (exam != null) {
				return exam;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String options_ans(int questionid) {
		String answer = null;
		try {
			entityMethod();
			String data = "from Options where question_id=:question_id1";
			Query query = manager.createQuery(data);
			query.setParameter("question_id1", questionid);
			List<Options> options = query.getResultList();
			for (int i = 0; i < options.size(); i++) {
				System.out.println((i + 1) + "." + options.get(i).getOptions());
			}
			System.out.println("Enter your ans");
			int ans = sc.nextInt();
			answer = options.get(ans - 1).getOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public Object questions(String language) {
		try {
			entityMethod();
			ExamTopic exam = exam(language);
			int exam_id = exam.getExam_id();
			String exam_name = exam.getExam_name();
			String data = "from Questions where exam_id=:exam_id1";
			Query query = manager.createQuery(data);
			query.setParameter("exam_id1", exam_id);

			List<Questions> question = query.getResultList();
			for (int i = 0; i < question.size(); i++) {
				System.out.println(question.get(i).getQuestion());
				int question_id = question.get(i).getQuestion_id();
				String result = options_ans(question_id);
				if (question.get(i).getAnswer().equalsIgnoreCase(result)) {
					flag++;
					System.out.println("-----------------");
				}
			}
			System.out.println("Your Final Score is " + flag + " in the " + language + " exam");
			result_insert(uid, flag, exam_name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (factory != null) {
				factory.close();
			}
			if (manager != null) {
				manager.close();
			}
		}
		return null;
	}

	public void result_insert(int uid, int score, String exam_name) {
		try {
			entityMethod();
			transaction = manager.getTransaction();
			transaction.begin();
			Results result_obj = new Results();
			result_obj.setUser_id(uid);
			result_obj.setScore(score);
			result_obj.setExam_name(exam_name);
			result_obj.setDate(date);
			manager.persist(result_obj);
			System.out.println("Result inserted");
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Results result(String language) {
		try {
			entityMethod();
			String data = "from Results where exam_name=:language and user_id=:uid1";
			Query query = manager.createQuery(data);
			query.setParameter("language", language);
			query.setParameter("uid1", uid);
			List<Results> result_list = query.getResultList();
			for (Results results : result_list) {
				System.out.println("User ID : " + results.getUser_id());
				System.out.println("Exam Name : " + results.getExam_name());
				System.out.println("Score : " + results.getScore() + " out of 4");
				System.out.println("Date : " + results.getDate());
				System.out.println("-----------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Results allExamResult() {
		try {
			entityMethod();
			String data = "from Results where user_id=:id";
			Query query = manager.createQuery(data);
			query.setParameter("id", uid);
			List<Results> results_list = query.getResultList();
			for (Results results : results_list) {
				System.out.println("Exam Name : " + results.getExam_name());
				System.out.println("Score : " + results.getScore());
				System.out.println("Date : " + results.getDate());
				System.out.println("-------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}