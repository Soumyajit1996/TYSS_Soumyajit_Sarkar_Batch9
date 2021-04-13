package com.tyss.quizpro.main;

import java.util.Scanner;

import com.tyss.quizpro.dao.QuizDaoimpl;
import com.tyss.quizpro.service.IQuizService;
import com.tyss.quizpro.service.QuizServiceImpl;

public class MainClass {
	static Scanner sc = new Scanner(System.in);

	public static boolean forResult() {
		while(true) {
			System.out.println("================");
			System.out.println("1.For individual results");
			System.out.println("2.All Results");
			System.out.println("3.Press Any for exit");
			System.out.println("Enter your choice");
			int key = sc.nextInt();
			System.out.println("================");
			switch (key) {
			case 1:
				IQuizService iService6 = new QuizServiceImpl();
				iService6.result();
				break;
			case 2:
				IQuizService iService7 = new QuizServiceImpl();
				iService7.allExamResult();
				break;
			default:
				System.out.println("Thanks");
				System.out.println("================");
				return true;
			}
		}
	}
	public static boolean forExam() {
		while (true) {
			System.out.println("================");
			System.out.println("What quiz you want to attend?");
			System.out.println("1.Java");
			System.out.println("2.Html");
			System.out.println("3.JS");
			System.out.println("4.Press Any For Exit");
			System.out.println("Enter your choice");
			int n2 = sc.nextInt();
			System.out.println("================");
			switch (n2) {
			case 1:
				String exam_type1 = "java";
				IQuizService iService3 = new QuizServiceImpl();
				iService3.questions(exam_type1);
				break;
			case 2:
				String exam_type2 = "html";
				IQuizService iService4 = new QuizServiceImpl();
				iService4.questions(exam_type2);
				break;
			case 3:
				String exam_type3 = "js";
				IQuizService iService5 = new QuizServiceImpl();
				iService5.questions(exam_type3);
				break;
			default:
				System.out.println("Exam Ended");
				System.out.println("Thank You");
				System.out.println("================");
				return true;
			}
		}
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println("================");
			System.out.println("1.Login");
			System.out.println("2.Register");
			System.out.println("3.Press Any for Exit");
			System.out.println("Enter your choice");
			int n1 = sc.nextInt();
			System.out.println("================");
			switch (n1) {
			case 1:
				IQuizService iService1 = new QuizServiceImpl();
				iService1.login();
				if(QuizDaoimpl.count>0) {
					while (true) {
						System.out.println("================");
						System.out.println("1.For Quiz");
						System.out.println("2.For Result");
						System.out.println("3.Press Any for Exit");
						System.out.println("Enter your choice");
						int n = sc.nextInt();
						System.out.println("================");
						switch (n) {
						case 1:
							forExam();
							break;
						case 2:
							forResult();
							break;
						default:
							System.out.println("Result ended");
							System.out.println("THANK YOU");
							System.out.println("================");
							break;
						}
						break;
					}
				}
				else {
					System.out.println("Invalid Email or password");
				}
				break;
			case 2:
				IQuizService iService2 = new QuizServiceImpl();
				iService2.registration();
				break;
			default:
				System.out.println("Quiz End");
				System.out.println("================");
				System.exit(0);
				break;
			}
		}
	}
}
