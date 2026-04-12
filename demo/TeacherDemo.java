package demo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import Book.Book;
import course.Course;
import course.CourseFile;
import course.Period;
import mark.AttestationType;
import other.Message;
import other.News;
import users.Database;
import users.Employee;
import users.FacultyType;
import users.Librarian;
import users.Manager;
import users.Student;
import users.Teacher;
import users.User;

public class TeacherDemo {
	static Teacher teacher = null;
	static BufferedReader br = null;
	
	private static void putMark() throws IOException {
		double point;
		AttestationType period = null;
		System.out.println("Enter course name: ");
		String courseName = br.readLine();
		
		System.out.println("Enter student name: ");
		String studentName = br.readLine();
		
		System.out.println("Chooose Attestation type: \n 1) First \n 2) Second \n 3) Exam");
		int num = Integer.parseInt(br.readLine());
		if (num == 1) 
			period = AttestationType.FIRST;
		else if (num == 2) 
			period = AttestationType.SECOND;
		else
			period = AttestationType.EXAM;
		
		System.out.println("Enter student point: ");
		point = Integer.parseInt(br.readLine());
		
		for(Course c : Database.courses) {
			if (c.getCourseName().equals(courseName)) {
				for (Student s : Database.students) {
					if (s.getFullName().equals(studentName)) {
						if (teacher.putMark(c, s, point, period));
							System.out.println("Book is ordered");
					}
				}
				
			}
		}
		System.out.println("Error");
	}
	
	private static void viewCourses() {
		System.out.println(teacher.viewCourses());
	}
	
	private static void viewStudents() {
		System.out.println(teacher.viewStudents());
	}
	
	private static void addCourseFile() throws IOException {
		String name;
		String text;
		Course course = null;
		System.out.println("Enter file name: ");
		name = br.readLine();
		
		System.out.println("Enter file text: ");
		text = br.readLine();
		
		System.out.println("Enter file course: ");
		String courseName = br.readLine();
		for (Course c : Database.courses) {
			if (c.getCourseName().equals(courseName))
				course = c;
		}
		
		if(teacher.addCourseFile(new CourseFile(name, text, course)));
			System.out.println("Course is successfully added!");
		
	}
	
	private static void removeCourseFile() throws IOException {
		System.out.println("Enter file name: ");
		String name = br.readLine();
		
		for (CourseFile c : Database.files) {
			if (c.getName().equals(name)) {
				if (teacher.removeCourseFile(c));
					System.out.println("Course file is successfully removed!");
			}
		}
		System.out.println("Course file is not in the Database!");
	}
	
	private static void exit() {
		Database.save();
		System.out.println("Bye bye");
	}
	
	public static void run(User user) {
		teacher = (Teacher)user;
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while(true) {
				System.out.println("What do you want to do?\n 1) put Mark() \n 2) View courses  \n 3) View students  \n 4) Change the course file \n  \n 5) Logout");
				int choice = Integer.parseInt(br.readLine());
				if (choice == 1) {
					putMark: while(true) {		
						putMark();	
						System.out.println("\n 1) Put mark to another student  \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue putMark;
						if(choice == 2) continue menu;
						if(choice == 3) {
							exit();  
							break menu;
						}
						break;
					}
				}
				else if (choice == 2) {
					viewCourses();
					System.out.println(teacher.viewCourses());
					
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(br.readLine());
					if(choice == 1) continue menu;
					if(choice == 2) {
						exit();  
						break menu;
					}
				}
				else if (choice == 3) {
					viewStudents();
					System.out.println(teacher.viewStudents());
					
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(br.readLine());
					if(choice == 1) continue menu;
					if(choice == 2) {
						exit();  
						break menu;
					}
				}
				else if (choice == 4) {
					System.out.println("What do you want to do?\n - add course file \n - remove course file");
					String operation = br.readLine();
					if (operation.equals("add news")) {
						addCourseFile: while(true) {
							addCourseFile();
							System.out.println("\n 1) Add another course file  \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(br.readLine());
							if(choice == 1) continue addCourseFile;
							if(choice == 2) continue menu;
							if(choice == 3) {
								exit();  
								break menu;
							}
							break;
						}
					}
					else if (operation.equals("remove news")) {
						removeCourseFile: while(true) {
							removeCourseFile();
							System.out.println("\n 1) Remove another course file  \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(br.readLine());
							if(choice == 1) continue removeCourseFile;
							if(choice == 2) continue menu;
							if(choice == 3) {
								exit();  
								break menu;
							}
							break;
						}
					}
				}
				else {
					exit();
					break menu;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Something bad happened... \n Saving resources...");
			e.printStackTrace();
		}
	}
}
